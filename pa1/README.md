# cmps012b-pt.w18/pa1

The following is a set of performance tests to run on your Recursion program.

## Installation

Run the following in your working directory (the directory you wrote your code
in) to get the test script and example input files:

```bash
curl https://raw.githubusercontent.com/legendddhgf/cmps012b-pt.w18.grading/master/pa1/pa1.sh > pa1.sh
chmod +x pa1.sh
```

## Usage

After installation, you can run the script with this line:

```bash
./pa1.sh
```

The tests will print out the difference between your output and the correct
output, using the `diff -bBwu` command. Lack of any output between the set of
"==========" means that your program is running correctly. Any lines prefixed
with `-` are from your own output, and are incorrect. Any lines prefixed with
`+` are from the correct output, and are missing in your output.

## Removal

The following command will remove all text files and shell scripts in your
directory. Since you should not have any files that end in `.txt` or `.sh`
anyway, this should serve to delete all the files we gave you. Note that a
backup folder is also created (called "Backup") and can be deleted if you
choose.

```bash
rm -f *.txt *.sh
```
