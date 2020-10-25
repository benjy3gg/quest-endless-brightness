# Quest Endless Brightness
This is a Quest App that sets the Screen-Brightness to 0 and keeps it there.
It's running as a background-service and sets it back to 0 every 5 seconds (if e.g. the System sets it to 100).

To get it running:
- install the apk (e.g. via SideQuest)
- go to "Unknown Sources"
- Start "com.benjy3gg.endlessbrightness"
- Allow the Permissions in the Popup for Changing System Settings (needed for setting the Display Brightness)
- In the App choose "Brightness 0" and the app will start the background service
- Done, the app will set the brightness to 0 every 5 seconds

- If you want to stop the service, just start the app again and choose Set Brightness Auto which will stop the service