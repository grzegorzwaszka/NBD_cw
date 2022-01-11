db.people.aggregate([ {$group: {_id: "$sex", avgheight: {$avg: { $toDouble: "$height"}}, avgweight: {$avg: { $toDouble: "$weight"}}}} ])
