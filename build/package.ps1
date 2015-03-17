$szpath = "$env:ProgramFiles\7-Zip\7z.exe"

$target = "export/3seventy-java-sdk.zip"

# Verify that 7-Zip is installed.
if (-not (test-path "$szpath")) { throw "$szpath needed" }
set-alias sz "$szpath"

# We don't care if the file actually exists or not, just make sure the file system is squeaky clean.
echo "Removing: $target"
rm -ErrorAction 'silentlycontinue' -Force $target

# Make sure we are excluding the package script and old examples (which TFS won't let us delete)
# We also exclude the bin directory, as the user can rebuild this themselves.
sz a -tzip $target -xr!bin -x!build -x!"370.java.example" -x!"3seventy.java.client"
