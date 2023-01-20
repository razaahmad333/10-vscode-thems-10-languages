# 🔖 Format Descriptor 🪢🪢🪢

### you are given a format , which is a string

    ",,1,1,,,,,,"

    "3,6,1,1,1,,"

### you have to find out a descriptor which gives the sheets for the format

```json

{
  "fontFamily": "'Arial', sans-serif",
  "fontSize": "10pt",
  "fontWeight": "bold",
  "fontStyle": "italic"
}

{
    "fontFamily": "'Verdana', sans-serif",
    "fontSize": "18pt",
    "fontWeight": "bold",
    "fontStyle": "italic",
    "textDecoration": "underline"
}

```

# Using the information given below

## Format Descriptor table

```txt
0 fontFamily ------------ 0 = Arial, default    "0,,,,,,,,,"
1 fontSize -------------- 0 = 10 pt, default    ",0,,,,,,,,"
2 bold ------------------ 1 = on                ",,1,,,,,,,"
3 italic ---------------- 1 = on                ",,,1,,,,,,"
4 underline ------------- 1 = on                ",,,,1,,,,,"
5 strikethrough --------- 1 = on                ",,,,,1,,,,"
6 horizontalAlign ------- 2 = center            ",,,,,,2,,,"
7 verticalAlign --------- 2 = middle            ",,,,,,,2,,"
8 color (text) ---------- 4 = #FEEEF0           ",,,,,,,,4,"
9 color (background) ---- 8 = #E6F5FE           ",,,,,,,,,8"
```

### and this hash table (map | Object | dictionary | Associative Array | json )

\*what ever you call this

```json

"formats": {
        "defaults": "0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0",
        "fontFamily": [
            {
                "name": "Arial",
                "traits": [
                    "sans-serif"
                ]
            },
            {
                "name": "Tahoma",
                "traits": [
                    "sans-serif"
                ]
            },
            {
                "name": "Times New Roman",
                "traits": [
                    "serif"
                ]
            },
            {
                "name": "Verdana",
                "traits": [
                    "sans-serif"
                ]
            }
        ],
       
    },


```

### Need More Help? [ click me](https://smartsheet-platform.github.io/api-docs/#formatting)

<br/>
<br/>

# How do I submit my solution?

- ### [click here to learn how to submit your solution](https://github.com/razaahmad333/coding-challenges/blob/main/CONTRIBUTING.md)

<br>

> ### want solutions
>
> [click here to get solution by me](https://github.com/razaahmad333/coding-challenges/blob/main/2022-06-26-Format_Descriptor/Solutions/Ahmad_Raza/index.js)

or you can checkout solutions by other people in dir given below

[2020-06-26-Format_Descriptor/Solutions](https://github.com/razaahmad333/coding-challenges/blob/main/2022-06-26-Format_Descriptor/Solutions)

### question is incomplete?
[raise an issue](https://github.com/razaahmad333/coding-challenges/issues/new)

# coding-challenges

Here, I share real-world coding problems which I encounter during internships and open source contributions.

[I have an interesting question to share](https://github.com/razaahmad333/coding-challenges#-i-have-an-interesting-question-to-share-)

<br>
<br>

> ## How do I submit my solution?

[see the link](https://github.com/razaahmad333/coding-challenges/blob/main/CONTRIBUTING.md)

or

### Here is few steps to follow

- ### Fork the repo

  - At the top right corner of the page, click on the `Fork` button

  - After forking, you will be redirected to a screen of creating a new repo with a `Create Fork` button.

  - Click on the `Create Fork` button.

- ### Clone the repo

  - After creating the repo, find and click on the `Clone` button.

  - Copy the link by clicking on the copy icon.

- ### Open your favorite terminal and run the following command

  ```shell
    git clone https://github.com/<your_github_username>/coding-challenges.git
  ```

  or

  ```shell
    git clone  git@github.com:<your_github_username>/coding-challenges.git
  ```

- ### Now open up your favourite text editor and Add your solution

  - Open the folder `coding-challenges`

  - Go to the question folder for the prblem you want to solve

    For example:

    problem: `2022-06-26-Format_Descriptor`

    question folder would be: `coding-challenges/2022-06-26-Format_Descriptor`

  - You will find out a Solutions folder inside that.

    For example: `coding-challenges/2022-06-26-Format_Descriptor/Solutions`

  - In this folder, create a new folder by your name

    For example: `coding-challenges/2022-06-26-Format_Descriptor/Solutions/YOUR_NAME`

  (you will see a folder named `Ahmad_Raza` already there, that's solution by me)

  - Here create any file you want, for your solution.
    with any extension (.js .py .cpp .txt etc)

  - after adding your solution, commit it to your repo

- ### How Should I Commit?

  open up a terminal and start type following commands

  - add all the files you have added to your repo

  ```shell
      git add .
  ```

  - commit

  ```shell
      git commit -m "i added my solution or any nice message you like"
  ```

  - push the code to your repo

  ```shell
      git push origin main
  ```

<br/>

- ### Now its time for pull request.

  - Go to your repo on GitHub and click on the `Pull Requests` button.

  - Click on the `New pull request` button.

  - Click on the `Create Pull Request` button.

  - Now write a short description of what you have done or leave it empty.

  - Find and Click on the ` Create pull request` button on that page.

<br>

# that's great 🎉🎉

Now I will merge your contribution to the main repo 🤗😻.

<br>
<br>

> # 💡 I have an interesting question to share 🤝

- 📩 send it to my email: **ahmadrazakhan8992@gmail.com**
- or 🏌️ create a new issue on this repo
- or 🖇️ give a link to the question
- or ✌️ give a pull request with the question in txt file or in any file you want
- or 🏠 message me on **[LinkedIn](https://www.linkedin.com/in/ahmadrazakhan946)** or **[twitter](https://twitter.com/ahmadraza946_)**