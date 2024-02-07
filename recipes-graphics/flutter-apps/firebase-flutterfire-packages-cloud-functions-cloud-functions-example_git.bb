#
# Copyright (c) 2020-2024 Joel Winarske. All rights reserved.
#

SUMMARY = "cloud_functions_example"
DESCRIPTION = "Demonstrates how to use the cloud_functions plugin."
AUTHOR = "Google"
HOMEPAGE = "None"
BUGTRACKER = "None"
SECTION = "graphics"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=93a5f7c47732566fb2849f7dcddabeaf"

SRCREV = "886c39a3e9d52d3bd4c9e34a215579188954ad48"
SRC_URI = "git://github.com/firebase/flutterfire.git;lfs=0;branch=master;protocol=https;destsuffix=git"

S = "${WORKDIR}/git"

PUB_CACHE_EXTRA_ARCHIVE_PATH = "${WORKDIR}/pub_cache/bin"
PUB_CACHE_EXTRA_ARCHIVE_CMD = "flutter pub global activate melos; \
    melos bootstrap"

PUBSPEC_APPNAME = "cloud_functions_example"
FLUTTER_APPLICATION_INSTALL_SUFFIX = "firebase-flutterfire-packages-cloud-functions-cloud-functions-example"
FLUTTER_APPLICATION_PATH = "packages/cloud_functions/cloud_functions/example"

inherit flutter-app
