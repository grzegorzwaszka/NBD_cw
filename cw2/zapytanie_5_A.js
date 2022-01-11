db.people.aggregate([ { $match: { nationality: "Poland" } },{$unwind: "$credit"}, {$group: { _id: "$credit.currency", sumBalance: { $sum: { $toDouble: "$credit.balance" }}}} ])
