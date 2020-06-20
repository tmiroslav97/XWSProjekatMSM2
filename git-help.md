## Git help (for command line)

To make git repo in folder
`git init` 

Cloning local repository
`git clone /path/to/repository`

Cloning remote repository
`git clone host:/path/to/repository`

Adding changes to index 
`git add *`

Commit changes 
`git commit -m  "Message about work that you done"`

Making and checkout to new branch
`git checkout -b branch_name`

Setting upstream branch after creating or after first commit
`git push --set-upstream origin branch_name`

Push to remote
`git push`

Update local repository from remote
`git pull`

Checkout master branch
`git checkout master`

Checkout any other branch
`git checkout branch_name`

Merging some branch with branch that we are checked out
`git merge branch_name`

**Some basic git workflow**
* If cloning remote git repository
    ```
    git clone host:/path/to/repository
    -enter credentials
    ```
    Else if making new repository
    ```
    git init
    git remote add origin host:/path/to/repository
    ```
    Else 
    ```
    -do nothing
    ```    
* Pull changes and merge master to yout branch
    ```
    git checkout master
    git pull
    git checkout branch_name
    git merge master
   ```
* Making new feature or bugfix branch branch_name 
    ```
    git checkout -b feature-unique_id-functionality
    git checkout -b bugfix-unique_id-functionality
   ```
* Commit
    ```
    git add *
    git commit -m 'Description of changes'
    git push <if upstream branch is set>
    git push origin branch_name <if upstream branch is not set>
   ```
* When you finish work on branch make pull request


   
   Links:  
   https://www.atlassian.com/git/tutorials/comparing-workflows/feature-branch-workflow