db.people.mapReduce( function() {emit(this.sex, {height: parseFloat(this.height), weight: parseFloat(this.weight)})}, function(sex, val) { return {height: Array.avg(val.map(x=>x.height)), weight: Array.avg(val.map(x=>x.weight))}}, {out: "peopleavg"} )
db.peopleavg.find()
