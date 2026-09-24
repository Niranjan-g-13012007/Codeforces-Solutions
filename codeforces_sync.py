import os
import requests
import time
import hashlib
import random
import base64
import re

HANDLE = "niranjan_gobinathan"

API_KEY = os.getenv("CODEFORCES_API_KEY")
API_SECRET = os.getenv("CODEFORCES_API_SECRET")

if not API_KEY or not API_SECRET:
    print("ERROR: Codeforces API credentials are not set.")
    exit(1)


def call_codeforces(method, params):

    rand = str(random.randint(100000, 999999))

    params["apiKey"] = API_KEY
    params["time"] = int(time.time())

    sorted_params = sorted(params.items())

    param_string = "&".join(
        f"{key}={value}" for key, value in sorted_params
    )

    signature_string = (
        rand
        + "/"
        + method
        + "?"
        + param_string
        + "#"
        + API_SECRET
    )

    signature = hashlib.sha512(
        signature_string.encode("utf-8")
    ).hexdigest()

    params["apiSig"] = rand + signature

    url = f"https://codeforces.com/api/{method}"

    response = requests.get(url, params=params)

    return response.json()


def clean_filename(name):

    # Remove characters that Windows does not allow
    name = re.sub(r'[<>:"/\\|?*]', '', name)

    return name


print("Checking Codeforces submissions...")

params = {
    "handle": HANDLE,
    "from": 1,
    "count": 100,
    "includeSources": "true"
}

data = call_codeforces("user.status", params)

if data.get("status") != "OK":

    print("Codeforces API error:")
    print(data)

    exit(1)


submissions = data["result"]

print(f"Found {len(submissions)} submissions.")


# Only accepted submissions
accepted = [
    submission
    for submission in submissions
    if submission.get("verdict") == "OK"
]

print(f"Accepted submissions: {len(accepted)}")


# Process oldest submissions first
for submission in reversed(accepted):

    contest_id = submission.get("contestId")

    problem = submission["problem"]

    index = problem["index"]

    name = problem["name"]

    rating = problem.get("rating")

    language = submission.get("programmingLanguage", "")

    source_base64 = submission.get("sourceBase64")


    # Skip if source code is unavailable
    if not source_base64:

        print(
            f"Skipping {contest_id}{index}: "
            "source unavailable"
        )

        continue


    # Only process Java submissions
    if "Java" not in language:

        print(
            f"Skipping {contest_id}{index}: "
            f"language = {language}"
        )

        continue


    # Skip problems without a rating
    if rating is None:

        print(
            f"Skipping {contest_id}{index}: "
            "problem has no rating"
        )

        continue


    # Decode source code
    try:

        source = base64.b64decode(
            source_base64
        ).decode("utf-8")

    except Exception as e:

        print(
            f"Could not decode {contest_id}{index}: {e}"
        )

        continue


    # Create rating folder
    #
    # 800  -> 0800
    # 900  -> 0900
    # 1000 -> 1000
    #
    rating_folder = f"{int(rating):04d}"

    os.makedirs(
        rating_folder,
        exist_ok=True
    )


    # Clean problem name
    clean_name = clean_filename(name)


    # Create filename
    #
    # Example:
    # 282A - Bit++.java
    #
    filename = (
        f"{contest_id}{index} - "
        f"{clean_name}.java"
    )


    filepath = os.path.join(
        rating_folder,
        filename
    )


    # Don't create duplicate files
    if os.path.exists(filepath):

        print(
            f"Already exists: {filepath}"
        )

        continue


    # Write source code
    with open(
        filepath,
        "w",
        encoding="utf-8"
    ) as file:

        file.write(source)


    print(
        f"Added: {filepath}"
    )


print()
print("Codeforces sync completed!")