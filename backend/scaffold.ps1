$packages = @("config", "security", "auth", "user", "driver", "supervisor", "vehicle", "terminal", "trip", "container", "approval", "notification", "dashboard", "report", "sync", "audit", "exception", "common")
$basePath = "src\main\java\com\smartitt"

foreach ($pkg in $packages) {
    $path = Join-Path $basePath $pkg
    New-Item -ItemType Directory -Force -Path (Join-Path $path "controller") | Out-Null
    New-Item -ItemType Directory -Force -Path (Join-Path $path "service") | Out-Null
    New-Item -ItemType Directory -Force -Path (Join-Path $path "repository") | Out-Null
    New-Item -ItemType Directory -Force -Path (Join-Path $path "entity") | Out-Null
    New-Item -ItemType Directory -Force -Path (Join-Path $path "dto") | Out-Null
}

Write-Host "Scaffolded backend packages successfully."
