package me.hql.linhscript.henv;

import java.util.ArrayList;
import java.util.List;

/**
 * Bộ cắt token cho tệp .henv — viết thuần Java (không phụ thuộc IntelliJ) để dễ kiểm thử.
 * Cú pháp bám sát đúng HqlEnv.parse() của LinhScript:
 *   # hoặc ; đầu dòng = chú thích, "export " đầu dòng, KHOÁ=giá trị (cũng nhận dấu :),
 *   nháy kép (escape + nhiều dòng + ${...}), nháy đơn (nguyên văn, nhiều dòng),
 *   giá trị trần (${...}, chú thích cuối dòng sau khoảng trắng + #).
 */
public final class HenvTokenizer {

    public enum Kind {
        WHITESPACE, COMMENT, EXPORT, KEY, SEPARATOR,
        VALUE, NUMBER, BOOLEAN,
        STRING, ESCAPE,
        INTERP_OPEN, INTERP_NAME, INTERP_OP, INTERP_DEFAULT, INTERP_CLOSE,
        BAD
    }

    /** Trạng thái lexer tại CUỐI token (0 = đầu dòng, an toàn để khởi động lại). */
    public static final int S_LINE_START = 0;
    public static final int S_IN_DQ = 1;
    public static final int S_IN_SQ = 2;
    public static final int S_MID_LINE = 3;

    public static final class Tok {
        public final Kind kind;
        public final int start, end, stateAfter;
        Tok(Kind kind, int start, int end, int stateAfter) {
            this.kind = kind; this.start = start; this.end = end; this.stateAfter = stateAfter;
        }
        @Override public String toString() { return kind + "[" + start + "," + end + ")s" + stateAfter; }
    }

    private final CharSequence s;
    private final int end;
    private int pos;
    private final List<Tok> out = new ArrayList<>();
    private int state;            // trạng thái hiện tại (đang ở đâu trong dòng)

    private HenvTokenizer(CharSequence s, int start, int end, int initialState) {
        this.s = s; this.pos = start; this.end = end; this.state = initialState;
    }

    public static List<Tok> tokenize(CharSequence text, int start, int end, int initialState) {
        HenvTokenizer t = new HenvTokenizer(text, start, end, initialState);
        t.run();
        return t.out;
    }

    // ------------------------------------------------------------------
    private void add(Kind k, int from, int to, int stateAfter) {
        if (to > from) out.add(new Tok(k, from, to, stateAfter));
    }

    private boolean isNl(char c) { return c == '\n' || c == '\r'; }
    private boolean isBlank(char c) { return c == ' ' || c == '\t'; }
    private boolean isKeyChar(char c) { return Character.isLetterOrDigit(c) || c == '_' || c == '.' || c == '-'; }

    private int lineEnd(int from) {
        int i = from;
        while (i < end && !isNl(s.charAt(i))) i++;
        return i;
    }

    private void run() {
        if (state == S_IN_DQ) { stringBody(true); }
        else if (state == S_IN_SQ) { stringBody(false); }
        else state = S_LINE_START;

        while (pos < end) {
            char c = s.charAt(pos);

            // khoảng trắng + xuống dòng (xuống dòng đưa về đầu dòng)
            if (isBlank(c) || isNl(c)) {
                int from = pos;
                boolean nl = false;
                while (pos < end && (isBlank(s.charAt(pos)) || isNl(s.charAt(pos)))) {
                    if (isNl(s.charAt(pos))) nl = true;
                    pos++;
                }
                if (nl) state = S_LINE_START;
                else if (state == S_LINE_START) state = S_LINE_START;
                else state = S_MID_LINE;
                // nếu có xuống dòng thì dòng tiếp theo bắt đầu sạch
                add(Kind.WHITESPACE, from, pos, state);
                continue;
            }

            // ---------- đầu dòng ----------
            if (c == '#' || c == ';') {
                int e = lineEnd(pos);
                add(Kind.COMMENT, pos, e, S_MID_LINE);
                pos = e;
                continue;
            }
            parseEntry();
        }
    }

    /** Một dòng KHOÁ=GIÁ TRỊ (pos đang ở ký tự đầu tiên khác trắng). */
    private void parseEntry() {
        int le = lineEnd(pos);

        // "export " + khoá
        if (startsWith("export", pos) && pos + 6 < le && isBlank(s.charAt(pos + 6))) {
            int k = pos + 6;
            while (k < le && isBlank(s.charAt(k))) k++;
            if (k < le && isKeyChar(s.charAt(k))) {
                add(Kind.EXPORT, pos, pos + 6, S_MID_LINE);
                add(Kind.WHITESPACE, pos + 6, k, S_MID_LINE);
                pos = k;
            }
        }

        // khoá
        int ks = pos;
        while (pos < le && isKeyChar(s.charAt(pos))) pos++;
        int ke = pos;
        int q = pos;
        while (q < le && isBlank(s.charAt(q))) q++;
        if (ke == ks || q >= le || (s.charAt(q) != '=' && s.charAt(q) != ':')) {
            // dòng hỏng: HqlEnv bỏ qua cả dòng
            add(Kind.BAD, ks, le, S_MID_LINE);
            pos = le;
            return;
        }
        add(Kind.KEY, ks, ke, S_MID_LINE);
        add(Kind.WHITESPACE, ke, q, S_MID_LINE);
        add(Kind.SEPARATOR, q, q + 1, S_MID_LINE);
        pos = q + 1;
        int w = pos;
        while (w < le && isBlank(s.charAt(w))) w++;
        add(Kind.WHITESPACE, pos, w, S_MID_LINE);
        pos = w;

        if (pos < end && s.charAt(pos) == '"') {
            int from = pos;
            pos++;
            state = S_IN_DQ;
            add(Kind.STRING, from, pos, S_IN_DQ);
            stringBody(true);
        } else if (pos < end && s.charAt(pos) == '\'') {
            int from = pos;
            pos++;
            state = S_IN_SQ;
            add(Kind.STRING, from, pos, S_IN_SQ);
            stringBody(false);
        } else {
            bareValue(le);
        }
    }

    // ------------------------------------------------------------------
    //  Giá trị trần: tới hết dòng, cắt chú thích cuối dòng (" #")
    // ------------------------------------------------------------------
    private void bareValue(int le) {
        int cut = le;
        for (int k = pos; k < le; k++) {
            if (s.charAt(k) == '#' && (k == pos || isBlank(s.charAt(k - 1)))) { cut = k; break; }
        }
        // phần giá trị thật (bỏ khoảng trắng đuôi)
        int ve = cut;
        while (ve > pos && isBlank(s.charAt(ve - 1))) ve--;

        if (ve > pos) {
            if (indexOf("${", pos, ve) < 0) {
                add(classify(pos, ve), pos, ve, S_MID_LINE);
            } else {
                int p = pos;
                while (p < ve) {
                    int o = indexOf("${", p, ve);
                    int c = o < 0 ? -1 : indexOfChar('}', o + 2, ve);
                    if (o < 0 || c < 0) { add(Kind.VALUE, p, ve, S_MID_LINE); p = ve; break; }
                    add(Kind.VALUE, p, o, S_MID_LINE);
                    interp(o, c);
                    p = c + 1;
                }
            }
        }
        pos = ve;
        if (cut > pos) {
            add(Kind.WHITESPACE, pos, cut, S_MID_LINE);
            pos = cut;
        }
        if (le > pos) {
            add(Kind.COMMENT, pos, le, S_MID_LINE);
            pos = le;
        }
        state = S_MID_LINE;
    }

    private Kind classify(int from, int to) {
        String v = s.subSequence(from, to).toString();
        if (v.matches("[-+]?\\d+(\\.\\d+)?")) return Kind.NUMBER;
        switch (v.toLowerCase()) {
            case "true": case "false": case "yes": case "no": case "on": case "off":
            case "y": case "n": case "co": case "có": case "khong": case "không":
                return Kind.BOOLEAN;
            default: return Kind.VALUE;
        }
    }

    /** ${TEN} hoặc ${TEN:-macDinh}; open = vị trí '$', close = vị trí '}'. */
    private void interp(int open, int close) {
        add(Kind.INTERP_OPEN, open, open + 2, S_MID_LINE);
        int d = indexOf(":-", open + 2, close);
        if (d < 0) {
            add(Kind.INTERP_NAME, open + 2, close, S_MID_LINE);
        } else {
            add(Kind.INTERP_NAME, open + 2, d, S_MID_LINE);
            add(Kind.INTERP_OP, d, d + 2, S_MID_LINE);
            add(Kind.INTERP_DEFAULT, d + 2, close, S_MID_LINE);
        }
        add(Kind.INTERP_CLOSE, close, close + 1, S_MID_LINE);
    }

    // ------------------------------------------------------------------
    //  Thân chuỗi nháy kép / nháy đơn (có thể kéo dài nhiều dòng)
    // ------------------------------------------------------------------
    private void stringBody(boolean dq) {
        int inState = dq ? S_IN_DQ : S_IN_SQ;
        char quote = dq ? '"' : '\'';
        int runStart = pos;
        while (pos < end) {
            char c = s.charAt(pos);
            if (c == quote) {
                pos++;                                   // nháy đóng thuộc về chuỗi
                add(Kind.STRING, runStart, pos, S_MID_LINE);
                state = S_MID_LINE;
                afterClosingQuote();
                return;
            }
            if (dq && c == '\\' && pos + 1 < end) {
                char nx = s.charAt(pos + 1);
                if (nx == 'n' || nx == 't' || nx == 'r' || nx == '"' || nx == '\\' || nx == '$') {
                    add(Kind.STRING, runStart, pos, inState);
                    add(Kind.ESCAPE, pos, pos + 2, inState);
                    pos += 2;
                    runStart = pos;
                    continue;
                }
            }
            if (dq && c == '$' && pos + 1 < end && s.charAt(pos + 1) == '{') {
                int close = -1;
                for (int k = pos + 2; k < end; k++) {
                    char d = s.charAt(k);
                    if (d == '}') { close = k; break; }
                    if (d == '"' || isNl(d)) break;
                }
                if (close > 0) {
                    add(Kind.STRING, runStart, pos, inState);
                    interpInString(pos, close, inState);
                    pos = close + 1;
                    runStart = pos;
                    continue;
                }
            }
            pos++;
        }
        // hết văn bản mà chưa đóng nháy: giữ trạng thái "đang trong chuỗi"
        add(Kind.STRING, runStart, pos, inState);
        state = inState;
    }

    private void interpInString(int open, int close, int st) {
        add(Kind.INTERP_OPEN, open, open + 2, st);
        int d = indexOf(":-", open + 2, close);
        if (d < 0) {
            add(Kind.INTERP_NAME, open + 2, close, st);
        } else {
            add(Kind.INTERP_NAME, open + 2, d, st);
            add(Kind.INTERP_OP, d, d + 2, st);
            add(Kind.INTERP_DEFAULT, d + 2, close, st);
        }
        add(Kind.INTERP_CLOSE, close, close + 1, st);
    }

    /** Sau nháy đóng: chỉ chấp nhận khoảng trắng + chú thích #; chữ khác bị HqlEnv bỏ qua. */
    private void afterClosingQuote() {
        int le = lineEnd(pos);
        int p = pos;
        while (p < le && isBlank(s.charAt(p))) p++;
        add(Kind.WHITESPACE, pos, p, S_MID_LINE);
        pos = p;
        if (pos < le) {
            add(s.charAt(pos) == '#' ? Kind.COMMENT : Kind.BAD, pos, le, S_MID_LINE);
            pos = le;
        }
    }

    // ------------------------------------------------------------------
    private boolean startsWith(String p, int at) {
        if (at + p.length() > end) return false;
        for (int i = 0; i < p.length(); i++) if (s.charAt(at + i) != p.charAt(i)) return false;
        return true;
    }

    private int indexOf(String p, int from, int to) {
        for (int i = from; i + p.length() <= to; i++) {
            boolean ok = true;
            for (int j = 0; j < p.length(); j++) if (s.charAt(i + j) != p.charAt(j)) { ok = false; break; }
            if (ok) return i;
        }
        return -1;
    }

    private int indexOfChar(char c, int from, int to) {
        for (int i = from; i < to; i++) if (s.charAt(i) == c) return i;
        return -1;
    }
}
