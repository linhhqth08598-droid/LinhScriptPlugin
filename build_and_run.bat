@echo off
chcp 65001 >nul
cd /d "%~dp0"

echo ==========================================
echo 1. Dang xoa thu muc src\main\gen...
echo ==========================================
if exist "src\main\gen" (
    rmdir /s /q "src\main\gen"
    echo Da xoa thu muc gen cu.
) else (
    echo Khong tim thay thu muc gen, bo qua.
)

echo.
echo ==========================================
echo 2. Dang sinh code Parser (.bnf) va Lexer (.flex)...
echo ==========================================
call gradlew.bat generateParser generateLexer

if %errorlevel% neq 0 (
    echo.
    echo [LOI] Sinh code tu .bnf hoac .flex that bai! Dung lai.
    pause
    exit /b %errorlevel%
)

echo.
echo ==========================================
echo 3. Dang build plugin (buildPlugin)...
echo ==========================================
call gradlew.bat buildPlugin --rerun-tasks

if %errorlevel% neq 0 (
    echo.
    echo [LOI] Qua trinh build that bai! Dung lai.
    pause
    exit /b %errorlevel%
)

echo.
echo ==========================================
echo 4. Build thanh cong! Dang khoi dong IDE (runIde)...
echo ==========================================
call gradlew.bat runIde

pause