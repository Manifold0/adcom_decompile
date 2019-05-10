// Created by Julian Dunskus

import Foundation

struct Balance: Decodable {
	var missions: [Mission]
	var ranks: [Rank]
	
	enum CodingKeys: String, CodingKey {
		case missions = "Missions"
		case ranks = "Ranks"
	}
}

struct Rank: Decodable {
	var number: Int
	var requiredMissionCount: Int
	
	init(from decoder: Decoder) throws {
		let container = try decoder.container(keyedBy: CodingKeys.self)
		try number = Int(container.decode(String.self, forKey: .number))!
		try requiredMissionCount = Int(container.decode(String.self, forKey: .requiredMissionCount))!
	}
	
	enum CodingKeys: String, CodingKey {
		case number = "Rank"
		case requiredMissionCount = "Missions"
	}
}
