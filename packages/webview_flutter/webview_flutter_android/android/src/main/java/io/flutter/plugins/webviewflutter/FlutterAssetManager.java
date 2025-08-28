// Copyright 2013 The Flutter Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

package io.flutter.plugins.webviewflutter;

import android.content.res.AssetManager;
import androidx.annotation.NonNull;
import io.flutter.embedding.engine.plugins.FlutterPlugin;

/** Abstraction over Flutter assets used by the webview plugin. */
public interface FlutterAssetManager {
  /** Returns the asset file path for a given logical asset name. */
  @NonNull
  String getAssetFilePathByName(@NonNull String name);

  /** Embedding v2 implementation backed by FlutterPlugin.FlutterAssets. */
  final class PluginBindingFlutterAssetManager implements FlutterAssetManager {
    private final AssetManager assetManager;
    private final FlutterPlugin.FlutterAssets flutterAssets;

    public PluginBindingFlutterAssetManager(
        @NonNull AssetManager assetManager, @NonNull FlutterPlugin.FlutterAssets flutterAssets) {
      this.assetManager = assetManager;
      this.flutterAssets = flutterAssets;
    }

    @Override
    @NonNull
    public String getAssetFilePathByName(@NonNull String name) {
      // Delegate to Flutter's asset resolver
      return flutterAssets.getAssetFilePathByName(name);
    }

    /** Exposes the raw Android AssetManager when needed elsewhere. */
    @NonNull
    public AssetManager getAndroidAssetManager() {
      return assetManager;
    }
  }

  // NOTE:
  // Legacy V1 embedding (Registrar) implementation has been removed to avoid
  // compile errors on modern Flutter/AGP. Use v2 embedding only.
}
