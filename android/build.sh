#!/bin/bash
# Ubuntu: sudo apt install openjdk-21-jdk-headless aapt apksigner zipalign libandroid-23-java dalvik-exchange
set -e
AJ=/usr/share/java/com.android.android-23.jar; DX=/usr/share/java/com.android.dx.jar
KS=${KS:-qolyozma-release.keystore}; PW=${PW:?PW (keystore paroli) kerak}
rm -rf build; mkdir -p build/classes
javac --release 8 -nowarn -cp $AJ -d build/classes src/uz/qolyozma/app/MainActivity.java
java -cp $DX com.android.dx.command.Main --dex --min-sdk-version=21 --output=build/classes.dex build/classes
aapt package -f -M AndroidManifest.xml -S res -A assets -I $AJ -F build/unsigned.apk
(cd build && aapt add unsigned.apk classes.dex >/dev/null && zipalign -f -p 4 unsigned.apk aligned.apk)
apksigner sign --ks "$KS" --ks-pass pass:$PW --key-pass pass:$PW --out build/Qolyozma.apk build/aligned.apk
echo "Tayyor: build/Qolyozma.apk"
