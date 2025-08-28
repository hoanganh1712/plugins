// Copyright 2013 The Flutter Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

package io.flutter.plugins.webviewflutter;

import androidx.annotation.NonNull;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.FlutterAssetManagerHostApi;

/**
 * Host api implementation for FlutterAssetManager.
 *
 * <p>Bridges calls from Dart (Pigeon) to the Android asset manager used by the plugin.
 */
public class FlutterAssetManagerHostApiImpl implements FlutterAssetManagerHostApi {
  final FlutterAssetManager flutterAssetManager;

  /** Constructs a new instance of {@link FlutterAssetManagerHostApiImpl}. */
  public FlutterAssetManagerHostApiImpl(@NonNull FlutterAssetManager flutterAssetManager) {
    this.flutterAssetManager = flutterAssetManager;
  }

  @Override
  @NonNull
  public List<String> list(@NonNull String path) {
    try {
      String[] paths = flutterAssetManager.list(path);
      if (paths == null) {
        return Collections.emptyList();
      }
      return Arrays.asList(paths);
    } catch (IOException ex) {
      throw new RuntimeException(ex.getMessage());
    }
  }

  @Override
  @NonNull
  public String getAssetFilePathByName(@NonNull String name) {
    return flutterAssetManager.getAssetFilePathByName(name);
    }
}
