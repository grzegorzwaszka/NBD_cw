var mapFunction2 = function() {
      for (var idx = 0; idx < this.credit.length; idx++) {
         var key = this.credit[idx].currency;
         var value = { valBalance: this.credit[idx].balance };
         emit(key, value);
      }
};

var reduceFunction2 = function(curr, objValues) {
   val = { valBalance: 0 };
   for (var idx = 0; idx < objValues.length; idx++) {
         val.valBalance += parseFloat(objValues[idx].valBalance);
   }
   return val;
};

db.people.mapReduce( mapFunction, reduceFunction, {query: {nationality: "Poland"}, out: "creditbalancepol"} );
db.creditbalancepol.find();
