# iCloudNotes

iCloudNotes is a reader for the Notes application in your Apple or Google account, it's only feature right now is to read the notes and save them as they are in a folder as html files, images are not processed.

## Build
build with maven with 
```sh
$ mvn package
```
## Run
from command line
```sh
$ java -jar iCloudNotes-0.19a.jar email_user_name password apple ./mynotes
```
### Parameters
Google: Application-specific password required: https://support.google.com/accounts/answer/185833

- email_user_name - NOT the complete e-mail address
- password 
- 'apple' for iCloud notes or 'google' for google notes

### run example
```sh
...
found 2 note folders
opening folder Notes/idea
saving ./mynotes/Notes_27_08_2015_04_07_21/idea/i.html
saving ./mynotes/Notes_27_08_2015_04_07_21/idea/TWITTER.html
opening folder Notes/projects
saving ./mynotes/Notes_27_08_2015_04_07_21/projects/Twitter client.html
saving ./mynotes/Notes_27_08_2015_04_07_21/projects/sar.html
saving ./mynotes/Notes_27_08_2015_04_07_21/projects/Surface_weather_analysis.html
saving ./mynotes/Notes_27_08_2015_04_11_51/projects/images/Screen Shot 2015-06-02 at 20.55.04.png
...
```
