# meta-flutter

Yocto Layer for Google Flutter related projects.

### Recommended development flow:
* Create flutter workspace using ./tools/setup_flutter_workspace.py
* Debug and validate application on host using flutter-auto, AGL QEMU, or Linux GTK.
* Create Yocto Recipe for your Flutter application using `flutter-gallery-*` or one of the many app recipes as the template.
  Nested projected are supported using FLUTTER_APPLICATION_PATH.
  Passing Dart defines are done with FLUTTER_EXTRA_BUILD_ARGS.
* Add your app, and selected embedder to your release image.  The flutter engine will be implicitly added to the image.
* Image device

## Layers dependencies

* meta-clang

Clang generates smaller runtime images, and is used by Google to build the the flutter engine for Android and iOS.  Changing toolchains should be avoided, as this would provide a path with little to no test milage.

## Overview

Target BSP is expected to have a GPU with OpenGLES v2.0+ support.  
If you selecting a part go with v3.0+, ideally one with Vulkan support.

## Notes

* There are no OSS Linux embedders (that I am aware of that currently support software rendering).  The engine does support it.

* `flutter-auto` is the `agl` branch of https://github.com/toyota-connected/ivi-homescreen
  the `main` branch has moved to quarterly releases, the `agl` branch is directly supporting AGL development work.

### CI Jobs

* dunfell-agl-aarch64.yml - AGL emaultor build that uses non-locked values.  This is used as health gate for rolling commits in AGL.  No Vulkan support.

* dunfell-agl-x86_64.yml - AGL emaultor build that uses non-locked values.  This is used as health gate for rolling commits in AGL.  No Vulkan support.

* dunfell-dart-mx8m-mini.yml - Variscite DART-MX8M-MINI (NXP iMX8) Weston build + ivi-homescreen.  No Vulkan support.

* dunfell-linux-dummy.yml - meta-flutter canary CI job builds all recipes against linux-dummy kernel

* dunfell-nvidia-jetson-nano-devkit.yml - NVidia Jetson Nano Devkit weston build with ivi-homescreen and Vulkan suport.

* dunfell-nvidia-jetson-xavier-nx-devkit.yml - NVidia Jetson Xavier NX Devkit weston build with ivi-homescreen and Vulkan suport.

* dunfell-rpi4-64.yml - Raspberry PI4 EGL/DRM build with Flutter-pi and Vulkan (Mesa 21.2) support.

* dunfell-stm32mp15.yml - ST Microelectronics STM32MP15 EGL and Weston builds.  EGL build has flutter-pi (currently has rendering issues), and Weston build has ivi-homescreen which works beautifully.

For more Raspberry PI images see the Honister branch.


### General

Targets flutter-engine-* is known to work on

* AGL QEMU images - aarch64/x86_64
* Intel icore7-64
* NVIDIA Nano, Xavier Dev Kits - aarch64
* NXP iMX7 (caveats), iMX8
* Qualcomm DragonBoard DB410c, DB820, SA6155P, SA8xxx - aarch64
* Raspberry Pi 3 / Compute - aarch64 / armv7hf
* Raspberry Pi 4 / Compute - aarch64
* Raspberry Pi ZeroW / Zero2W - aarch64
* Renesas R-Car M3/H3 - aarch64
* STM32MP157x - cortexa7t2hf
* etc

## Include the Flutter SDK into Yocto SDK

Add to local.conf file:

    TOOLCHAIN_HOST_TASK_append = " nativesdk-flutter-sdk"

Then run:

    bitbake <image name> -c populate_sdk


Note: when using SDK you may need to add the following after installation:

    $ export SDK_ROOT=<install folder>/sysroots/x86_64-nodistrosdk-linux/usr/share/flutter/sdk
    $ git config --global --add safe.directory $SDK_ROOT

## General Yocto Notes

When building on systems with GCC version > than uninative in Yocto distro add the following to conf/local.conf

    INHERIT_remove = "uninative"

## Flutter Workspace Automation
Please visit [here](tools/README.md) for how to setup Flutter workspace automatically.
