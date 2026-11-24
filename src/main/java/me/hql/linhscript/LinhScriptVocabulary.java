package me.hql.linhscript;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LinhScriptVocabulary {

    public record CompletionItem(String text, String category) {}

    // LOẠI 1: Core Built-ins
    public static final Set<String> TYPE_1_CORE = new HashSet<>(Arrays.asList(
            "print", "input", "call", "type", "str", "num", "bool", "random",
            "add", "size", "len", "push", "pop", "trim", "strip", "lstrip", "rstrip",
            "split", "substring", "toLowerCase", "toUpperCase", "indexOf", "lastIndexOf",
            "startsWith", "endsWith", "replace", "insert", "removeAt", "join",
            "map", "filter", "reduce"
    ));

    // LOẠI 2: Đầy đủ toàn bộ hàm & method của tất cả thư viện HQL
    public static final Set<String> TYPE_2_LIBRARIES = new HashSet<>(Arrays.asList(
            // Math & Time
            "sqrt", "pow", "round", "ceil", "floor", "abs", "sin", "cos", "tan",
            "max", "min", "cbrt", "trunc", "toRadians", "hypot", "log", "log10",
            "log2", "factorial", "gcd", "lcm", "comb", "clamp", "isqrt", "fsum",
            "isclose", "isnan", "isinf", "perm", "degrees", "exp", "mean", "median",
            "variance", "stddev", "correlation", "linearRegression", "matrixMultiply",
            "matrixTranspose", "matrixDeterminant", "matrixInverse", "unitConvert",
            "listUnits", "celsiusToFahrenheit", "fahrenheitToCelsius", "unitCompare",
            "now", "today", "currentTime", "format", "parse", "addMinutes", "addDays",
            "addMonths", "addYears", "daysBetween", "getYear", "getMonth", "getDay",
            "getWeekday", "age", "isWeekend", "isPast", "countdown",

            // IO & System
            "read", "write", "readA", "writeA", "create", "exists", "delete",
            "copy", "move", "fileSize", "getName", "extension", "isDir",
            "lastModified", "list", "listFull", "find", "freeSpace", "totalSpace",
            "homeDir", "workingDir", "zip", "unzip",
            "cpuModel", "cpuCores", "cpuThreads", "cpuSpeed", "cpuVendor",
            "machineModel", "gpuModel", "gpuMemory", "gpuDriver", "resolution",
            "totalRam", "freeRam", "usedRam", "ramSpeed", "mainboard",
            "biosVersion", "serialNumber", "osName", "osArch", "userName",
            "uptime", "exec", "execRead", "exit", "env", "gc",
            "diskName", "diskStatus", "diskTemp", "diskWear",

            // Net & WebSocket & Database
            "wsStart", "wsGuiTatCa", "wsSoKetNoi", "wsStop",
            "httpGet", "httpPost", "httpQuery", "httpPostFile", "download",
            "isOnline", "addHeader", "setCookie", "setTimeout", "setProxy",
            "ignoreSSL", "getStatusCode", "getContentType", "getResponseHeader",
            "getIP", "getHost", "ping", "checkPort", "createServer",
            "connect", "closeDB", "isOpen", "query", "querySafe", "execute",
            "executeSafe", "identity", "procedure", "beginTransaction", "commit",
            "rollback", "tables", "columns", "exportCSV", "fetchOne",
            "dbError", "queryValue",

            // Web (HQLSP + Servlet)
            "printWeb", "webStart", "webStop", "webWait", "webRoot", "webRoute",
            "webServlet", "webExportTomcat", "webContext", "param", "params",
            "webMethod", "webPath", "webQuery", "webBody", "webIP", "reqHeader",
            "webStatus", "webType", "resHeader", "webRedirect", "webInclude",
            "webRender", "webJson", "webHienLoiChiTiet", "webBaoLoi",
            "webJsonText", "webAnh", "webImage", "webNhiPhan", "webBinary",
            "sessionSet", "sessionGet", "sessionDel", "sessionId", "sessionKill",
            "cookieSet", "cookieGet", "htmlEscape", "urlEncode", "urlDecode", "webMime",

            // UI & Game & Mạng & 2.5D Iso & Media
            "alert", "confirm", "prompt", "chooseFile", "chooseColor", "window",
            "setLayout", "show", "hide", "close", "button", "label", "textField",
            "textArea", "image", "setStyle", "getText", "checkbox", "radioButton",
            "comboBox", "onHover", "onKeyPress", "trayInit", "table", "setInterval",
            "progressBar",
            "gameWindow", "setTitle", "closeGame", "clear", "drawRect", "fillRect",
            "drawCircle", "fillCircle", "drawLine", "drawText", "drawImage",
            "drawImageEx", "imageReady", "gameWindowFull", "screenW", "screenH",
            "isMousePressed", "sprite", "moveTo", "moveBy", "collide",
            "removeSprite", "spritePos", "isKeyDown", "isKeyPressed", "pressKey",
            "releaseKey", "mouseX", "mouseY", "isMouseDown", "gameLoop",
            "playSound", "randomInt", "distance", "frameCount", "rotateSprite",
            "scaleSprite", "stopSound", "setVolume", "setCamera", "getTextWidth",
            "mangMoServer", "mangNhan", "mangGui", "mangPhat", "mangDanhSachKhach",
            "mangSoKhach", "mangDuoiKhach", "mangDongServer", "mangKetNoi",
            "mangGuiServer", "mangNhanServer", "mangDaNoi", "mangNgatKetNoi",
            "mangIpMay",
            "mapNap", "mapO", "mapOTai", "mapSoCot", "mapSoHang", "mapKichThuocO",
            "mapDatO", "mapThayDuoc", "banTia", "themKhoi", "xoaKhoiHet",
            "trongKhoi", "veSuongMu", "veVungNhin", "veBanDo", "veBanDoNho",
            "isoCaiDat", "isoX", "isoY", "isoManHinhX", "isoManHinhY",
            "isoNguocVeTheGioi", "isoVeBanDo", "isoVeAnh", "isoVeAnhNen",
            "isoVeElip", "isoVeVienElip", "isoVeDuong", "isoVeSuongMu",
            "anConTro", "layChuVuaGo", "xoaChuVuaGo",
            "fillOval", "drawOval", "fillRoundRect", "drawRoundRect", "fillArc",
            "drawArc", "fillTriangle", "fillPolygon", "drawPolygon",
            "gradientRect", "drawTextCenter", "textWidth", "setFont",
            "setAlpha", "setStroke", "setFPS", "getFPS", "deltaTime", "camX",
            "camY", "beginUI", "endUI", "screenShake", "spawnParticle", "burst",
            "floatText", "clearEffects", "saveScreenshot", "tone", "playMusic",
            "stopMusic", "setMusicVolume", "playSfx", "randomFloat", "lerp", "angleTo",
            "loadImage", "saveImage", "resizeImage", "cropImage", "rotateImage",
            "flipImage", "grayscaleImage", "blurImage", "invertImage",
            "getPixelColor", "setPixelColor", "imageWidth", "imageHeight",
            "imageToSvg", "svgToImage",
            "anhTuFile", "imageFileToBase64", "anhRaFile", "imageBase64ToFile",
            "anhLamSach", "imageBase64Clean", "anhKieu", "imageBase64Mime",
            "anhDataUri", "imageBase64Uri", "anhDungLuong", "imageBase64Size",
            "anhHopLe", "imageBase64Valid", "anhRong", "imageBase64Width",
            "anhCao", "imageBase64Height", "anhThongTin", "imageBase64Info",
            "anhThuNho", "imageBase64Resize", "anhCatVuong", "imageBase64Square",
            "drawBarChart", "drawLineChart", "drawPieChart", "drawScatterChart",
            "drawHistogram", "setChartSize", "setChartColor", "saveChart", "chartTitle",

            // Utils & Advanced (Regex, Crypto, Config, CSV, XML, Binary, Color, Socket, Schedule, Mail, Export, Env, App)
            "sort", "reverse", "shuffle", "unique", "count", "md5", "sha256",
            "base64Encode", "base64Decode", "uuid", "toJson", "fromJson",
            "jsonEscape", "sleep", "range",
            "matches", "containsRegex", "findRegex", "findAll", "replaceRegex",
            "replaceFirst", "splitRegex", "group", "groups", "compile", "quote",
            "encrypt", "decrypt", "generateKeys", "sign", "verify",
            "secureRandom", "pbkdf2",
            "loadConfig", "getConfig", "setConfig", "saveConfig", "listConfigKeys",
            "hasConfigKey", "removeConfigKey", "getSectionConfig",
            "setSectionConfig", "listSections", "listSectionKeys", "removeSection",
            "readCsvFile", "writeCsvFile", "readCsvRecords", "writeCsvRecords",
            "readDelimitedFile", "writeDelimitedFile", "csvRowCount",
            "csvColumnCount", "getCsvCell", "setCsvCell", "getCsvField",
            "setCsvField", "addCsvRow", "addCsvRecord", "removeCsvRow",
            "removeCsvRecord", "sortCsvByColumn", "sortCsvRecordsByField",
            "filterCsvByColumn", "filterCsvRecordsByField", "csvRecordsToJson",
            "jsonToCsvRecords",
            "xmlReadFile", "xmlWriteFile", "xmlParseString", "xmlToString",
            "xmlCreateRoot", "xmlXpathQuery", "xmlXpathAttr", "xmlFindNodes",
            "xmlGetNode", "xmlGetAttr", "xmlToJson", "jsonToXml", "xmlToCsv",
            "csvToXml", "xmlSetNode", "xmlSetAttr", "xmlAddChild",
            "xmlRemoveNode", "xmlRemoveAttr", "xmlChildrenCount", "xmlHasNode",
            "binaryRead", "binaryWrite", "binaryReadByte", "binaryWriteByte",
            "binaryFileSize", "binaryPack", "binaryUnpack", "binaryPackToFile",
            "binaryUnpackFromFile", "binaryHexDump", "binaryHexToBytes",
            "binaryBytesToHex", "binaryXor", "binaryCRC32", "binaryMD5",
            "binarySHA256", "binaryReadBytes", "binaryWriteBytes",
            "binaryCalcSize", "binarySlice", "binaryConcat", "binaryRepeat",
            "binaryToBase64", "binaryFromBase64", "binaryReverse", "binaryCompare",
            "colorRgbToHex", "colorHexToRgb", "colorRgbToHsv", "colorHsvToRgb",
            "colorRgbToHsl", "colorHslToRgb", "colorRgbToCmyk", "colorCmykToRgb",
            "colorBlend", "colorMultiBlend", "colorGradient", "colorMultiGradient",
            "colorRandomGradient", "colorLighten", "colorDarken", "colorSaturate",
            "colorDesaturate", "colorAdjustHue", "colorLuminance",
            "colorContrastRatio", "colorIsLight", "colorIsDark", "colorDistance",
            "colorSimilarity", "colorToGreyscale", "colorRandomHex",
            "colorRandomRgb", "colorRandomPalette", "colorNameToRgb",
            "colorRgbToName", "colorWithOpacity", "colorExtractPalette",
            "socketTcpConnect", "socketTcpSend", "socketTcpReceive",
            "socketTcpClose", "socketTcpStatus", "socketTcpListen",
            "socketTcpAccept", "socketTcpSendTo", "socketTcpBroadcast",
            "socketTcpDisconnectClient", "socketUdpBind", "socketUdpSend",
            "socketUdpReceive", "socketUdpClose", "socketUdpBroadcast",
            "socketUdpMulticastJoin", "socketUdpMulticastLeave",
            "socketSslTcpConnect", "socketSslTcpListen", "socketSslTcpAccept",
            "socketSslTcpSend", "socketSetTimeout", "socketSetTcpNoDelay",
            "socketSetKeepAlive", "socketResolveHost", "socketGetLocalIp",
            "socketGetRemoteIp", "socketGetRemotePort", "socketListInterfaces",
            "socketGetHostName", "socketRawPing", "socketRawSend",
            "socketWebSocketConnect", "socketWebSocketSend",
            "socketWebSocketReceive", "socketSendFile", "socketReceiveFile",
            "socketEnableEncryption", "socketDisableEncryption",
            "socketSetNonBlocking", "socketWaitData", "socketSetOption",
            "taskRunOnce", "taskRunAt", "taskRepeat", "taskRepeatFixedDelay",
            "taskCancel", "taskCancelAll", "taskExists", "taskCron",
            "taskCronAt", "taskCronUntil", "cronValidate", "cronNextRun",
            "taskPause", "taskResume", "taskFireNow", "taskReschedule",
            "taskSetMaxRuns", "taskChain", "taskDepend", "taskOnSuccess",
            "taskOnFailure", "taskWeekly", "taskMonthly", "taskLastDayOfMonth",
            "taskHoliday", "taskSaveState", "taskLoadState",
            "taskEnablePersistence", "taskGetRuns", "taskResetStats",
            "taskRunParallel", "taskAcquireLock", "taskReleaseLock",
            "taskWaitFor", "taskBroadcastEvent", "taskList", "taskDetails",
            "taskCount", "taskActiveCount", "taskPauseAll", "taskResumeAll",
            "taskShutdown",
            "mailConfig", "mailSenderName", "mailIsConfigured", "mailError",
            "mailAttach", "mailClearAttach", "mailOtp", "mailPassword",
            "mailIsValid", "mailSend", "mailSendText", "mailSendAsync",
            "mailSendAsyncText", "mailConfigEnv", "mailWarmUp",
            "exportXlsx", "exportDocx", "exportPdf", "exportFile", "webExport",
            "envLoad", "envGet", "envHas", "envSet", "envInt", "envBool",
            "envAll", "envFiles", "envRequire", "connectEnv", "dbPoolSize",
            "dbPoolStats", "executeBatch",
            "appWindow", "appIsOpen", "appWait", "appClose", "appHasBrowser",
            "processRun", "processAlive", "processKill", "waitPort",
            "portIsOpen", "sysProp", "appRootDir", "appTray",
            "pos", "scroll", "press", "release", "combo", "findPixel",
            "getClip", "setClip",
            "logInfo", "logWarning", "logError", "logDebug",
            "setLogLevel", "setLogFile", "logReset"
    ));

    // LOẠI 3: Hằng số
    public static final Set<String> TYPE_3_CONSTANTS = new HashSet<>(Arrays.asList(
            "PI", "E", "TAU", "INF", "NAN", "KM_TO_MILE", "MILE_TO_KM",
            "INCH_TO_CM", "CM_TO_INCH", "KG_TO_POUND", "POUND_TO_KG",
            "CELSIUS_OFFSET", "LITRE_TO_GALLON", "GALLON_TO_LITRE",
            "METER_TO_FEET", "FEET_TO_METER"
    ));

    // LOẠI 4: Core Logic
    public static final Set<String> TYPE_4_CORE = new HashSet<>(Arrays.asList(
            "if", "else", "for", "while", "switch", "case", "default",
            "break", "continue", "return", "in", "to", "is", "not",
            "null", "func", "var", "val", "class", "extends", "new",
            "try", "catch", "finally", "throw", "undefined", "map", "filter", "reduce"
    ));

    public static boolean isBuiltinCallable(String name) {
        return name != null && (TYPE_1_CORE.contains(name) || TYPE_2_LIBRARIES.contains(name));
    }

    public static boolean isBuiltinConstant(String name) {
        return name != null && TYPE_3_CONSTANTS.contains(name);
    }

    public static List<CompletionItem> completionItems() {
        List<CompletionItem> items = new ArrayList<>();
        TYPE_1_CORE.forEach(kw -> items.add(new CompletionItem(kw, "Core Method")));
        TYPE_2_LIBRARIES.forEach(fn -> items.add(new CompletionItem(fn, "Library Function")));
        TYPE_3_CONSTANTS.forEach(cn -> items.add(new CompletionItem(cn, "Constant")));
        TYPE_4_CORE.forEach(kw -> items.add(new CompletionItem(kw, "Logic")));
        return items;
    }
}