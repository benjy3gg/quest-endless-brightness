# Quest Endless Brightness
This is a Quest App that sets the Screen-Brightness to 0 and keeps it there.
It's running as a background-service and sets it back to 0 every 60 seconds (if e.g. the System sets it to 100).

To get it running:
- install the apk (e.g. via SideQuest)
- go to "Unknown Sources"
- Start "com.benjy3gg.endlessbrightness"
- Allow the Permissions in the Popup for Changing System Settings (needed for setting the Display Brightness)
- In the App choose either "Brightness 0" or select the brightness with the slider.
- Press the "Save" Button and your new brightness setting will be saved.
- Done, the app will set the brightness to 0 every 60 seconds

- If you want to stop the service, just start the app again and choose Set Brightness Auto which will stop the service
