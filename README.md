# Open-Emojiplatform

### How to run with Android Studio

Either pair up your Android phone (https://developer.android.com/studio/run/device) or set up a virtual device (https://developer.android.com/studio/run/managing-avds). Then press the Run button.

TODO: Add more detailed instructions.

### How to run with Visual Studio Code
Disclaimer, using Visual Studio Code is difficult because it relies on the Android Command Line Tools, a bunch of extensions and some custom scripts. Only do this if you really want to.
First, install the [Android Command Line Tools](https://developer.android.com/studio) at the bottom of the page or use a package manager to install it.
Next you need to install the necessary dependencies using `sdkmanager`:
```bash
sdkmanager "emulator" "platform-tools" "build-tools;34.0.0"
```
After that make sure to add both the `emulator` and `platform-tools` folders to your PATH. They should be located inside the folder for the Android Command Line Tools.

You also need to install an Android version and a system image for the emulator. These can be included in the command above. For, example here I am installing Android 14 (the newest version) and the google play arm64 system image (because I have an arm64 processor):
```bash
sdkmanager "platforms;android-34" "system-images;android-34;google_apis_playstore;arm64-v8a"
```
This is the same as choosing a system image in the Android Studio AVD Manager, just without a GUI. The image above is equivalent to the Pixel 6a with Google Play and Android 14.

Then you need to create an emulator using the `avdmanager`:
```bash
avdmanager create avd -n "Pixel6a -k "system-images;android-34;google_apis_playstore;arm64-v8a" -d "pixel_6a"
```
This will create an emulator named "Pixel6a" using the system image we installed earlier. You can also use the `avdmanager` to list, delete and modify emulators.

Now you can run the emulator using the `emulator` command:
```bash
emulator -avd "Pixel6a"
```

These are all the tools you need to run the app, but it still is a bad development experience since you need to manually run the emulator and you don't get the nice debugging features of Android Studio, or any syntax highlighting or autocompletion. To fix this we first need to install a handful of extensions:
- [Kotlin Language](https://marketplace.visualstudio.com/items?itemName=mathiasfrohlich.Kotlin) - for syntax highlighting and code snippets.
- [Kotlin](https://marketplace.visualstudio.com/items?itemName=fwcd.kotlin) - code completion, references, hover, go-to-definition and semantic highlighting.
- [XML](https://marketplace.visualstudio.com/items?itemName=redhat.vscode-xml) - for the XML layout files, provides syntax highlighting, code snippets and formatting.
- [Android](https://marketplace.visualstudio.com/items?itemName=adelphes.android-dev-ext) - for debugging the app.
- [Android iOS Emulator](https://marketplace.visualstudio.com/items?itemName=DiemasMichiels.emulate) - for running the emulator easily through vscode.
- [Gradle Language Support](https://marketplace.visualstudio.com/items?itemName=naco-siren.gradle-language) - for syntax highlighting and code snippets in the gradle files.
- [Trigger Task on Save](https://marketplace.visualstudio.com/items?itemName=Gruntfuggly.triggertaskonsave) - this is to run our custom scripts when we save a file.

Now that you have all the extension we need to set everything up. Firstly when developing you need to start the emulator using the `Android iOS Emulator` extension. Make sure to provide the correct emulator path to the extension in the vscode settings.
When that is setup we need to create some scripts for the debugger, for building and running the app and for linting using ktlint. There are extensions for formatting available, but I couldn't get them to work with our Ktlint gradle plugin.

#### Scripts
All scripts should be put in .vscode.
Firstly we need to create a `launch.json` file for the debugger. This is the file that tells the debugger how to run the app. Here is an example:
```json
{
  "version": "0.2.0",
  "configurations": [
    {
      "type": "android",
      "request": "launch",
      "name": "Launch",
      "preLaunchTask": "build",
      "appSrcRoot": "${workspaceRoot}/app/src/main",
      "apkFile": "${workspaceRoot}/app/build/outputs/apk/debug/app-debug.apk",
      "adbPort": 5037
    },
    {
      "type": "android",
      "request": "attach",
      "name": "Attach",
      "appSrcRoot": "${workspaceRoot}/app/src/main",
      "adbPort": 5037,
      "processId": "${command:PickAndroidProcess}"
    }
  ]
}
```
This file tells the debugger to run the app using the `build` task we will create and to use the `app-debug.apk` file as the app. The `adbPort` is the port that the debugger uses to communicate with the emulator. You can use the `Attach` configuration to attach to a running process if you want to debug a specific part of the app after it has started.

Now for building and formatting with ktlint we need to create a `tasks.json` file.
```json
{
  "version": "2.0.0",
  "tasks": [
    {
      "label": "ktlintFormat",
      "type": "shell",
      "command": "./gradlew ktlintFormat",
      "group": "build",
      "presentation": {
        "reveal": "silent"
      },
      "problemMatcher": ["$msCompile"]
    },
    {
      "label": "build",
      "type": "shell",
      "command": "if adb shell getprop sys.boot_completed | grep -q 1; then ./gradlew installDebug; else echo \"Error: No emulator found.\"; exit 1; fi",
      "dependsOn": "ktlintFormat",
      "group": {
        "kind": "build",
        "isDefault": true
      },
      "presentation": {
        "reveal": "silent"
      },
      "problemMatcher": ["$msCompile"]
    }
  ]
}
```
This file tells vscode to run the `ktlintFormat` task before the `build` task. The `ktlintFormat` task runs the ktlint formatter and the `build` task builds and installs the app on the emulator. I added an error message if there is no emulator running. You may have to change the shell tpye based on your computer. The `problemMatcher` is used to parse the output of the gradle tasks and to show errors and warnings in the vscode problems tab. You can run the `build` task manually by pressing `Ctrl+Shift+B`, but I have also set it to run automatically when I save a file using the `Trigger Task on Save` extension. This means that the code gets formatted and the app gets built and installed on the emulator every time I save a file.

This is the configuration in the vscode settings for the `Trigger Task on Save` extension:
```json
  "triggerTaskOnSave.tasks": {
    "build": ["app/src/**/*.kt", "app/src/**/*.xml"]
  },
  ```
This tells the extension to run the `build` task when I save a file in the `app/src` folder with the `.kt` or `.xml` extension. The build task triggers the `ktlintFormat` before it runs.


### Commit message title conventions:

- no capitalization,
- imperative verbs ("update README.md", not "updated README.md").

### Code Quality
- Use ktlint to format your code. You can run `./gradlew ktlintFormat` to format your code and set up your editor to run ktlint on save to make it easier.
- Use camelCase for variable names and function names.