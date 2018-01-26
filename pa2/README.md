# cmps012b-pt.w18/pa2

The following is a set of performance tests to run on your Search program.

## Installation

Run the following in your working directory (the directory you wrote your code
in) to get the test script and example input files:

```bash
curl https://raw.githubusercontent.com/legendddhgf/cmps012b-pt.w18.grading/master/pa2/pa2.sh > pa2.sh
chmod +x pa2.sh
```

## Usage

After installation, you can run the script with this line:

```bash
./pa2.sh
```

The tests will first print out the difference between your output and the
correct output, using the `diff -bBwu` command. Lack of any output between the
set of "==========" means that your program is running correctly. Any lines
prefixed with `-` are from your own output, and are incorrect. Any lines
prefixed with `+` are from the correct output, and are missing in your output.
Note that if you use the easy method you will lose 10 points and be warned
accordingly.

Next the tests will include 5 unit tests from Model*MergeTest.java which will
provide information on which tests were passed / whether you had an exception
in your code / your final score for the unit tests. It should autodetect which
function prototypes you used and work similarly regardless of which you chose.

## Removal

Everything that the script generates or downloads should automatically be
deleted by the script so if you would like to delete all files related to the
script, use the following. Note that the script leaves the backup folder for you
and you may choose to delete it if you like.

```bash
rm -f pa2.sh backup
```
