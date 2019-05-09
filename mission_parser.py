import json
import string

import pandas as pd
import numpy as np
from tqdm import tqdm

with open("Json Genie_ Balance.f989ca83f9296439f7c04509f8a88b40.json", "r") as json_file:
    full_dict = json.load(json_file)

missions = full_dict["Missions"]
ranks = full_dict["Ranks"]

condition_id_list = []
condition_type_list = []
threshold_list = []
rank_list = [int(rank["Missions"]) for rank in ranks]
reward_list = []

for mission in tqdm(missions):
    condition = mission["Condition"]
    condition_id_list.append(condition["ConditionId"])
    condition_type_list.append(condition["ConditionType"])
    threshold_list.append(float(condition["Threshold"]))
    reward_list.append(mission["Reward"]["RewardId"])

df = pd.DataFrame({
    "condition_id": condition_id_list,
    "condition_type": condition_type_list,
    "threshold": threshold_list,
    "reward": reward_list
})

df.head()

rank_thresholds = list(np.cumsum(rank_list))
rank_thresholds[-1] += 2

annotations = ["", "Thousand", "M", "B", "T"]
for i in range(26):
    annotations.append(2*string.ascii_uppercase[i])
for i in range(26):
    annotations.append(3*string.ascii_uppercase[i])


def scientific_to_stupid(value: float):
    i = 0
    while value >= 1000:
        i += 1
        value /= 1000

    stupid = annotations[i]
    if stupid == "Thousand":
        value *= 1000
        stupid = ""
    return f"{int(value):,} {stupid}".strip()


for i in range(len(condition_type_list)):
    cond_type = condition_type_list[i]
    if cond_type == "ResearchersUpgradedSinceSubscription":
        cond_string = "Upgrade Cards"
    elif cond_type == "ResourceQuantity":
        cond_string = f"Collect {condition_id_list[i].capitalize()}"
    elif cond_type == "ResourcesEarnedSinceSubscription":
        cond_string = f"Earn {condition_id_list[i].capitalize()}"
    elif cond_type == "ResearcherCardsEarnedSinceSubscription":
        cond_string = "Collect Cards"
    elif cond_type == "IndustryUnlocked":
        cond_string = f"Unlock {condition_id_list[i].capitalize()}"
    elif cond_type == "TradesSinceSubscription":
        cond_string = f"Trade {condition_id_list[i].capitalize()}"
    if i in rank_thresholds:
        print(f"Rank {rank_thresholds.index(i) + 1}")
    print(f"{cond_string} ({scientific_to_stupid(threshold_list[i])})")
