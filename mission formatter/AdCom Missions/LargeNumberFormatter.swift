// Created by Julian Dunskus

import Foundation

final class LargeNumberFormatter: NumberFormatter {
	static let suffixes = ["", "k", "M", "B", "T"] + "ABCDEFGHIJKLMNOPQRSTUVWXYZ".map { String([$0, $0]) }
	
	override init() {
		super.init()
		
		usesGroupingSeparator = true
		groupingSize = 3
		groupingSeparator = ","
		
		maximumFractionDigits = 3
	}
	
	required init?(coder aDecoder: NSCoder) {
		fatalError("ugh")
	}
	
	override func string(from number: NSNumber) -> String? {
		guard number.doubleValue >= 1_000_000 else { return super.string(from: number)! }
		
		let suffixes = LargeNumberFormatter.suffixes
		
		var index = 0
		var value = number.doubleValue
		while suffixes.indices.contains(index + 1) && abs(value) >= 1_000 {
			value /= 1000
			index += 1
		}
		
		let numberString = super.string(from: value as NSNumber)!
		return "\(numberString) \(suffixes[index])"
	}
}
