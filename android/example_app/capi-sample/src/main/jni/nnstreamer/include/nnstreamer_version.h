/* SPDX-License-Identifier: LGPL-2.1-only */
/**
 * NNStreamer Version Identifier, Updated by meson/mk scripts
 * Copyright (C) 2020 MyungJoo Ham <myungjoo.ham@samsung.com>
 */

/**
 * @file	nnstreamer_version.h
 * @date	12 Jun 2020
 * @brief	NNStreamer Version Identifier
 * @see		http://github.com/nnstreamer/nnstreamer
 * @author	MyungJoo Ham <myungjoo.ham@samsung.com>
 * @bug		No known bugs except for NYI items
 *
 * @detail 	meson or nnstreamer.mk file will update this.
 */
#ifndef __NNSTREAMER_VERSION_H__
#define __NNSTREAMER_VERSION_H__
#define NNSTREAMER_VERSION_MAJOR	(1)
#define NNSTREAMER_VERSION_MINOR	(5)
#define NNSTREAMER_VERSION_MICRO	(3)
#if NNSTREAMER_VERSION_MAJOR < 1
#error Version is not configured properly. Check if you are using proper build scripts (meson or build-android.lib.sh).
#endif
#endif
