db.people.mapReduce( function() {emit(this.job, {exists:1})}, function(job, val) { return 1}, {out: "peoplejob"} )
