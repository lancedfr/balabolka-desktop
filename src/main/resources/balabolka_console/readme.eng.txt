Balabolka (Console Application), version 1.23
Copyright (c) 2013-2015 Ilya Morozov
All Rights Reserved

WWW: http://www.cross-plus-a.com/bconsole.htm
E-mail: crossa@list.ru

Licence: Freeware
Operating System: Microsoft Windows XP/Vista/7/8
Speech API: 4.0/5.0 and above
Microsoft Speech Platform



*** Usage ***

balabolka_console.exe [options ...]


*** Command Line Options ***

-l
   Prints the list of available voices.

-g
   Prints the list of available audio output devices.

-f <text_file>
   Sets the name of input text file.

-w <wave_file>
   Sets the name of output file in WAV format. If the option is specified, an audio file will be created. Otherwise, text will be read aloud.

-n <voice_name>
   Sets the voice name (the part of the name will be enough). If the option is not specified, the voice, defined by the option [-id], or the default voice of Windows will be used.

-id <integer>
   Sets Locale ID for the voice. Locale ID is the language code, assigned by Microsoft (for example, "1033" for "English - United States").
   The program will choose the first voice with defined Locale ID from the list of voices. If the option is not specified, the voice, defined by the option [-n], or the default voice of Windows will be used.
   The list of Locale IDs: http://msdn.microsoft.com/en-us/goglobal/bb964664.aspx

-m
   Prints the parameters of the voice.

-b <integer>
   Sets the audio output device by its index. The index of the default audio device is 0.

-c
   Takes the text input from clipboard.

-t <text>
   Text input can be taken from the command line.

-i
   Takes the text input from STDIN.

-o
   SAPI 4: not used.
   SAPI 5 and Microsoft Speech Platform: writes sound data to STDOUT; if the option is specified, the option [-w] is ignored.

-s <integer>
   SAPI 4: sets the speed in a range of 0 to 100 (no default value).
   SAPI 5 and Microsoft Speech Platform: sets the speed in a range of -10 to 10 (the default is 0).

-p <integer>
   SAPI 4: sets the pitch in a range of 0 to 100 (no default value).
   SAPI 5 and Microsoft Speech Platform: sets the pitch in a range of -10 to 10 (the default is 0).

-v <integer>
   SAPI 4: not used.
   SAPI 5 and Microsoft Speech Platform: sets the volume in a range of 0 to 100 (the default is 100).

-e <integer>
   Sets the length of pauses between sentences (in milliseconds). The default is 0.

-a <integer>
   Sets the length of pauses between paragraphs (in milliseconds). The default is 0.

-d <file_name>
   Uses a dictionary for pronunciation correction (*.REX or *.DIC). The command line may contain few options [-d].

-k
   Kills other copies of the console application in the computer's memory.

-ka
   Kills the active copy of the console application in the computer's memory.

-q
   Adds the application to a queue. The console application will wait until other copies of the program have finished.

-lrc
   SAPI 4: not used.
   SAPI 5 and Microsoft Speech Platform: creates the LRC file, if the option [-w] or [-o] is specified.

-sub
   SAPI 4: not used.
   SAPI 5 and Microsoft Speech Platform: text will be processed as subtitles. 
   The option may be useful, when the options [-i] or [-c] are specified.

-tray
   Show the program's icon in the system tray. This allows to view the progress of the task.
   The context menu item "Stop" can be used to stop the process.

-ln <integer>
   Selects a line from the text file by using of a line number. The line numbering starts at "1".
   The interval of numbers can be used for selecting of more than one line (for example, "26-34").
   The command line may contain few options [-ln].

-? or -h
   Prints the list of available command line options.

--encoding <encoding> or -enc <encoding>
   Sets the input text encoding ("ansi", "utf8" or "unicode"). The default is "ansi".

--silence-begin <integer> or -sb <integer>
   Sets the length of silence at the beginning of the audio file (in milliseconds). The default is 0.

--silence-end <integer> or -se <integer>
   Sets the length of silence at the end of the audio file (in milliseconds). The default is 0.

--lrc-length <integer>
   Sets the maximal length of text lines for the LRC file (in characters).

--lrc-filename <file_name>
   Sets the name of the LRC file. The option may be useful, when the option [-o] is specified.

--lrc-encoding <encoding>
   Sets the encoding for the LRC file ("ansi", "utf8" or "unicode"). The default is "ansi".

--lrc-offset <integer>
   Sets the time shift for the LRC file (in milliseconds).

--lrc-artist <text>
   Sets the ID tag for the LRC file: artist.

--lrc-album <text>
   Sets the ID tag for the LRC file: album.

--lrc-title <text>
   Sets the ID tag for the LRC file: title.

--lrc-author <text>
   Sets the ID tag for the LRC file: author.

--lrc-creator <text>
   Sets the ID tag for the LRC file: creator of the LRC file.

--raw
   SAPI 4: not used.
   SAPI 5 and Microsoft Speech Platform: output is raw PCM; audio data does not contain the WAV header.
   The option is used together with the option [-o].

--ignorelength
   SAPI 4: not used.
   SAPI 5 and Microsoft Speech Platform: omits the length of data in the WAV header.
   The option is used together with the option [-o].

--sub-format <text>
   Sets the format of subtitles ("srt", "ssa", "ass" or "smi"). If the option is not specified, the format will be determined through the file extension.

--sub-fit pr -sf
   Automatically increases the speed to fit time intervals (when the program converts subtitles to audio file).

--sub-max <integer> or -sm <integer>
   Sets the maximal speed of speech in a range of -10 to 10 (when the program converts subtitles to audio file).

--deletefile or -df
   Removes the text file, when job is done. The option is used together with the option [-f].


*** Examples ***

balabolka_console.exe -l

balabolka_console.exe -n "Microsoft Anna" -m

balabolka_console.exe -f "d:\Text\book.txt" -w "d:\Sound\book.wav" -n "Emma"

balabolka_console.exe -n "Callie" -c -d "d:\rex\rules.rex" -d "d:\dic\rules.dic"

balabolka_console.exe -n "Emily" -t "The text will be read slowly." -s -5 -v 70

balabolka_console.exe -k

balabolka_console.exe -f "d:\Text\book.txt" -w "d:\Sound\book.wav" -lrc --lrc-length 80 --lrc-title "The Lord of the Rings"

balabolka_console.exe -f "d:\Text\film.srt" -w "d:\Sound\film.wav" -n "Laura" --sub-fit --sub-max 2


The example of use together with LAME.EXE:

balabolka_console.exe -f d:\book.txt -n Heather -o --raw | lame.exe -r -s 22.05 -m m -h - d:\book.mp3


The example of use together with OGGENC2.EXE:

balabolka_console.exe -f d:\book.txt -n Heather -o --ignorelength | oggenc2.exe --ignorelength - -o d:\book.ogg


The example of use together with WMAENCODE.EXE:

balabolka_console.exe -f d:\book.txt -n Heather -o --ignorelength | wmaencode.exe - d:\book.wma --ignorelength


*** Configuration File ***

The command line options can be stored as a configuration file "balabolka_console.cfg" in the same folder as the console application.

Configuration file example:
===============
-f d:\Text\book.txt
-w d:\Sound\book.wav
-n Microsoft Anna
-s 2
-p -1
-v 95
-e 300
-d d:\rex\rules.rex
-d d:\dic\rules.dic
-lrc
--lrc-length 75
--lrc-encoding utf8
--lrc-offset 300
===============

The program may combine options from the configuration file and the command line.


*** Audio Clips ***

The application allows to insert links to external WAV files (audio clips) into text. Audio clip tag will look like:

{{Audio=C:\Sounds\ring.wav}}

When speaking text aloud, the program will pause when the audio clip tag is reached, play the audio clip and resume speaking.
When converting to audio files, the audio clip will be embedded in the audio file created by the application.

###