printjson(db.people.find({birth_date: { $gt: "2001-01-01T01:01:01Z" }}, {first_name: 1, last_name: 1, "location.city": 1}).toArray())
