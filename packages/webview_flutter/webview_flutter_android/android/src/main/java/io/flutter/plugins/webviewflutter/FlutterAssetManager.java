// Copyright 2013 The Flutter Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

package io.flutter.plugins.webviewflutter;

import android.content.res.AssetManager;
import androidx.annotation.NonNull;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import java.io.IOException;

/** Abstraction over Flutter assets used by the webview plugin (embedding v2 only). */
public interface FlutterAssetManager {
  /** Returns the asset file path for a given logical asset name. */
  @NonNull
  String getAssetFilePathByName(@NonNull String name);

  /** Lists asset entries under the given path (delegates to Android AssetManager.list). */
  @NonNull
  String[] list(@NonNull String path) throws IOException;

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
      return flutterAssets.getAssetFilePathByName(name);
    }

    @Override
    @NonNull
    public String[] list(@NonNull String path) throws IOException {
      return assetManager.list(path);
    }

    /** Exposes the raw Android AssetManager when needed elsewhere. */
    @NonNull
    public AssetManager getAndroidAssetManager() {
      return assetManager;
    }
  }

  // NOTE: Legacy V1 (Registrar) implementation has been removed.
}
