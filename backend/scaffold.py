import os

base_pkg = "src/main/java/com/smartitt"
packages = [
    "config", "security", "auth", "user", "driver", "supervisor", "vehicle",
    "terminal", "trip", "container", "approval", "notification", "dashboard",
    "report", "sync", "audit", "exception", "common"
]

for pkg in packages:
    pkg_path = os.path.join(base_pkg, pkg)
    os.makedirs(os.path.join(pkg_path, "controller"), exist_ok=True)
    os.makedirs(os.path.join(pkg_path, "service"), exist_ok=True)
    os.makedirs(os.path.join(pkg_path, "repository"), exist_ok=True)
    os.makedirs(os.path.join(pkg_path, "entity"), exist_ok=True)
    os.makedirs(os.path.join(pkg_path, "dto"), exist_ok=True)

print("Scaffolded backend packages successfully.")
