// Created by Julian Dunskus

import Foundation

struct Mission: Decodable {
	var id: String
	var rank: Int
	var condition: Condition
	var reward: Reward
	
	enum CodingKeys: String, CodingKey {
		case condition = "Condition"
		case id = "Id"
		case rank = "Rank"
		case reward = "Reward"
	}
	
	struct Condition: Decodable, CustomStringConvertible {
		var id: String
		var type: ConditionType
		var threshold: Threshold
		
		var description: String {
			var typeDesc: String
			switch type {
			case .spendResources:
				assert(id == "darkscience")
				typeDesc = "⚗️ Spend Dark Science"
			case .earnResources:
				let pretty = [
					"fuel": "Fuel",
					"spacerock": "Space Rocks",
					"laser": "Lasers",
				]
				typeDesc = "💰 Collect \(pretty[id]!)"
			case .upgradeResearchers:
				typeDesc = "⤴️ Upgrade Cards"
			case .unlockIndustry:
				let pretty = [
					"exploration": "Exploration",
					"spaceforce": "Space Force",
				]
				typeDesc = "🔓 Unlock \(pretty[id]!)"
			case .collectCards:
				assert(id == "any")
				typeDesc = "🃏 Collect Cards"
			case .resourceQuantity:
				let pretty = [
					"launchpad": "Launch Pads",
					"technician": "Technicians",
					"trooper": "Troopers",
					"terraformer": "Terraformers",
					"autopilot": "Autopilots",
					"warpdrive": "Warp Drives",
					"starportal": "Star Portals",
					"rover": "Rovers",
					"chemist": "Chemists",
					"alienartifact": "Alien Artifacts",
					"planetliberator": "Planet Liberators",
					"rocket": "Rockets",
					"spaceprobe": "Space Probes",
					"cosmocannon": "Cosmo Cannons",
					"motherlandship": "Motherland Ships",
				]
				typeDesc = "📈 Collect \(pretty[id]!)"
			case .makeTrades:
				let pretty = [
					"fuel": "Fuel",
				]
				typeDesc = "🤝 Trade \(pretty[id]!)"
			}
			
			return "\(typeDesc) (\(threshold))"
		}
		
		enum CodingKeys: String, CodingKey {
			case id = "ConditionId"
			case type = "ConditionType"
			case threshold = "Threshold"
		}
		
		enum ConditionType: String, Decodable {
			case spendResources = "ResourcesSpentSinceSubscription"
			case earnResources = "ResourcesEarnedSinceSubscription"
			case upgradeResearchers = "ResearchersUpgradedSinceSubscription"
			case unlockIndustry = "IndustryUnlocked"
			case collectCards = "ResearcherCardsEarnedSinceSubscription"
			case resourceQuantity = "ResourceQuantity"
			case makeTrades = "TradesSinceSubscription"
		}
		
		struct Threshold: Decodable, CustomStringConvertible {
			static let formatter = LargeNumberFormatter()
			
			var value: Double
			
			var description: String {
				return Threshold.formatter.string(from: value as NSNumber)!
			}
			
			init(from decoder: Decoder) throws {
				let container = try decoder.singleValueContainer()
				if let int = try? container.decode(Int.self) {
					value = Double(int)
				} else {
					value = Double(try container.decode(String.self))!
				}
			}
		}
	}
	
	struct Reward: Decodable {
		var id: String
		var reward: String
		var value: Int
		
		enum CodingKeys: String, CodingKey {
			case id = "RewardId"
			case reward = "Reward"
			case value = "Value"
		}
	}
}
