// Created by Julian Dunskus

import Foundation

let jsonURL = URL(fileURLWithPath: Bundle.main.path(forResource: "balance", ofType: "json")!)
let jsonData = try Data(contentsOf: jsonURL)
let balance = try JSONDecoder().decode(Balance.self, from: jsonData)

let missionsPerRank = [2] // probably tutorial? doesn't work out without this
	+ balance.ranks.map { $0.requiredMissionCount }.dropFirst()
assert(missionsPerRank.reduce(0, +) == balance.missions.count)

var rank = 0
var missionsCompleted = 0
for mission in balance.missions {
	print(mission.condition)
	
	missionsCompleted += 1
	if missionsCompleted == missionsPerRank[rank] {
		missionsCompleted = 0
		rank += 1
		print()
		print("**RANK \(rank)**")
	}
}

//this was helpful for setting up all the pretty-printing names:
/*
let types = Dictionary(grouping: balance.missions) { $0.condition.type }
for (type, missions) in types {
	print("IDs for missions of type \(type):")
	dump(Set(missions.map { $0.condition.id }))
}
*/
