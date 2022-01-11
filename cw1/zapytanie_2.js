printjson(db.people.find({ nationality: "China", sex: "Female"}).limit(1).toArray())
