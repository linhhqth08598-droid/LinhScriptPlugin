package me.hql.linhscript.henv;

import java.security.SecureRandom;

/**
 * Nội dung .henv mẫu do plugin sinh ra.
 * CỐ Ý khác .henv.example của dự án (không GearZone, không SQL Server, không mail):
 * đây là bản mẫu "ứng dụng quản lý thư viện" vừa dùng được, vừa minh hoạ đủ cú pháp.
 * Mỗi lần tạo, JWT_SECRET và API_KEY được sinh ngẫu nhiên nên không bao giờ trùng nhau.
 */
public final class HenvTemplates {

    private static final SecureRandom RNG = new SecureRandom();

    private HenvTemplates() {}

    /** Tên chế độ lấy từ tên tệp: ".henv.production" → "production"; ".henv" → "development". */
    static String modeOf(String fileName) {
        String n = fileName.toLowerCase();
        int i = n.indexOf(".henv.");
        if (i >= 0) {
            String m = fileName.substring(i + 6);
            if (!m.isEmpty() && !m.equalsIgnoreCase("local") && !m.equalsIgnoreCase("example")) return m;
        }
        return "development";
    }

    static String randomHex(int bytes) {
        StringBuilder sb = new StringBuilder(bytes * 2);
        for (int i = 0; i < bytes; i++) sb.append(String.format("%02x", RNG.nextInt(256)));
        return sb.toString();
    }

    public static String build(String fileName) {
        String mode = modeOf(fileName);
        boolean prod = mode.equalsIgnoreCase("production") || mode.equalsIgnoreCase("prod");
        boolean local = fileName.toLowerCase().endsWith(".local");
        boolean example = fileName.toLowerCase().endsWith(".example");

        String vaiTro = local ? "RIÊNG TỪNG MÁY (ưu tiên cao hơn .henv, KHÔNG đưa lên git)"
                : example ? "BẢN MẪU để đưa lên git (không chứa mật khẩu thật)"
                : prod ? "CHẾ ĐỘ CHẠY THẬT — chọn bằng biến hệ điều hành HQL_ENV=" + mode
                : "GIÁ TRỊ CHUNG CỦA DỰ ÁN";

        String secret = example ? "doi-chuoi-nay-truoc-khi-chay" : randomHex(24);
        String apiKey = example ? "dien-api-key-o-day" : "hql_" + randomHex(12);

        return ""
                + "# ==========================================================================\n"
                + "#  CẤU HÌNH MÔI TRƯỜNG LINHSCRIPT — " + fileName + "\n"
                + "#  Vai trò: " + vaiTro + "\n"
                + "#  Tạo bởi plugin LinhScript. Đọc bằng: use hql.env;  envGet(\"APP_NAME\")\n"
                + "#  Biến môi trường thật của hệ điều hành luôn thắng giá trị trong tệp này.\n"
                + "# ==========================================================================\n"
                + "\n"
                + "# ---- Ứng dụng ----\n"
                + "APP_NAME=\"Quản Lý Thư Viện\"           # nháy kép: dùng được \\n \\t \\\" \\\\\n"
                + "APP_ENV=" + mode + "                    # development | staging | production\n"
                + "APP_DEBUG=" + (prod ? "false" : "true") + "\n"
                + "APP_VERSION=1.0.0\n"
                + "APP_LANG=vi\n"
                + "APP_TIMEZONE=Asia/Ho_Chi_Minh\n"
                + "\n"
                + "# ---- Máy chủ web ----\n"
                + "SERVER_HOST=" + (prod ? "0.0.0.0" : "127.0.0.1") + "\n"
                + "SERVER_PORT=" + (prod ? "80" : "8080") + "\n"
                + "SERVER_URL=http://${SERVER_HOST}:${SERVER_PORT}      # ${TEN} nối giá trị biến khác\n"
                + "SERVER_TIMEOUT_S=30\n"
                + "\n"
                + "# ---- Cơ sở dữ liệu (ví dụ MySQL) ----\n"
                + "DB_TYPE=mysql                          # sqlserver | mysql | postgres | sqlite\n"
                + "DB_HOST=localhost\n"
                + "DB_PORT=3306\n"
                + "DB_NAME=thu_vien\n"
                + "DB_USER=${DB_USER_MAC_DINH:-root}      # ${TEN:-macDinh}: dự phòng khi biến chưa có\n"
                + "DB_PASSWORD=doi-mat-khau-nay\n"
                + "DB_POOL_MAX=5\n"
                + "DB_POOL_MIN=1\n"
                + "\n"
                + "# ---- Bảo mật (đã sinh ngẫu nhiên cho tệp này) ----\n"
                + "JWT_SECRET=" + secret + "\n"
                + "API_KEY=" + apiKey + "\n"
                + "TOKEN_TTL_MIN=" + (prod ? "30" : "120") + "\n"
                + "\n"
                + "# ---- Tệp và nhật ký ----\n"
                + "UPLOAD_DIR=./uploads\n"
                + "MAX_UPLOAD_MB=10\n"
                + "LOG_LEVEL=" + (prod ? "warn" : "debug") + "                      # debug | info | warn | error\n"
                + "LOG_FILE='logs/app #1.log'             # nháy đơn: giữ nguyên từng chữ, kể cả dấu #\n"
                + "\n"
                + "# ---- Cờ bật/tắt (envBool hiểu true/1/yes/on/y/co và false/0/no/off/n/khong) ----\n"
                + "FEATURE_REGISTER=yes\n"
                + "FEATURE_EXPORT_EXCEL=on\n"
                + "FEATURE_BETA=" + (prod ? "no" : "co") + "\n"
                + "\n"
                + "# ---- Các kiểu viết khác cũng được chấp nhận ----\n"
                + "export HQL_ENV_NOTE=chu-export-o-dau-duoc-bo-qua\n"
                + "db.hostname=localhost                  # khoá có dấu chấm kiểu .properties\n"
                + "LOI_CHAO=\"Xin chào\n"
                + "bạn đã đến thư viện\"                    # giá trị nhiều dòng: mở nháy ở dòng này, đóng ở dòng sau\n"
                + "GIA_TIEN_THUC=\"\\${khong-noi-bien}\"       # \\$ để viết nguyên chữ ${...}\n";
    }
}
