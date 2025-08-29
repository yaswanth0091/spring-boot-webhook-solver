# Set the root directory of your project (where src folder is or should be created)
$projectRoot = Get-Location
$srcMainJava = Join-Path $projectRoot "src\main\java"

# Create src/main/java if it doesn't exist
if (-not (Test-Path $srcMainJava)) {
    New-Item -ItemType Directory -Path $srcMainJava | Out-Null
}

# Find all .java files in the project (recursively)
Get-ChildItem -Path $projectRoot -Recurse -Filter "*.java" | ForEach-Object {
    $file = $_.FullName

    # Read package declaration (if exists)
    $packageLine = Select-String -Path $file -Pattern "^\s*package\s+([\w\.]+);" | Select-Object -First 1

    if ($packageLine) {
        # Extract package path
        $packageName = $packageLine.Matches[0].Groups[1].Value
        $packagePath = $packageName -replace '\.', '\'

        # Destination folder
        $destFolder = Join-Path $srcMainJava $packagePath

        # Create folder if not exists
        if (-not (Test-Path $destFolder)) {
            New-Item -ItemType Directory -Path $destFolder -Force | Out-Null
        }

        # Move file into correct package folder
        Move-Item -Path $file -Destination $destFolder -Force
        Write-Output "Moved: $($_.Name) -> $destFolder"
    }
    else {
        # If no package declaration, put file in default package root
        Move-Item -Path $file -Destination $srcMainJava -Force
        Write-Output "Moved (no package): $($_.Name) -> $srcMainJava"
    }
}
