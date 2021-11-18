# sCommon

![](https://img.shields.io/github/languages/top/sunilpaulmathew/sCommon)
![](https://img.shields.io/github/contributors/sunilpaulmathew/sCommon)
![](https://img.shields.io/github/license/sunilpaulmathew/sCommon)

sCommon is a library sharing common code-base for the personal app projects of [sunilpaulmathew](https://github.com/sunilpaulmathew/sCommon/). Although it is made for personal use, sCommon is open and publically available in JitPack repository for anyone interested.

## Download

Step 1: Add it in your root-level build.gradle at the end of repositories:
```
allprojects {
        repositories {
                ...
                maven { url 'https://jitpack.io' }
        }
}
```

Step 2: Add dependency to the app-level build.gradle:
```
dependencies {
        implementation 'com.github.sunilpaulmathew:sCommon:Tag'
}
```
*Please Note: **Tag** should be replaced with the latest **[commit id](https://github.com/sunilpaulmathew/sCommon/commits/master)**.*

## What is included

sCommon includes several methods for doing simple to complex tasks. Please have a look on each entries in the [Utils](https://github.com/sunilpaulmathew/sCommon/tree/master/library/src/main/java/in/sunilpaulmathew/sCommon/Utils) folder for more information. However, please keep in mind that some of them requires specific permissions (e.g. **sInstallerUtils** requires "*android.permission.REQUEST_INSTALL_PACKAGES*") to be declared in the manifest file.

## Translations
Please help me to translate this library so that most of my application will get the benefits. The original strings for translation are hosted at [POEditor](https://poeditor.com/join/project?hash=9AiLut8Dmy). You may also translate after downloading the original language string available [here](library/src/main/res/values/strings.xml).

## License

    Copyright (C) 2021-2022 sunilpaulmathew <sunil.kde@gmail.com>

    sCommon is a free softwares: you can redistribute it and/or modify it
    under the terms of the GNU General Public License as published by the
    Free Software Foundation, either version 3 of the License, or (at
    your option) any later version.

    sCommon is distributed in the hope that it will be useful, but WITHOUT
    ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
    FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
    for more details.

    You should have received a copy of the GNU General Public License along
    with sCommon. If not, see <http://www.gnu.org/licenses/>.
    
[![GNU GPLv3](https://www.gnu.org/graphics/gplv3-127x51.png)](https://www.gnu.org/licenses/gpl-3.0.en.html)
