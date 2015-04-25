var a =  6378137;
var f = 1/298.257223563;
var b = (1-f)*a;
var fi1 = 59;
var fi2 = 60;
var lambda1 = 17;
var lambda2 = 18;

var L = (lambda2 - lambda1) * Math.PI/180;
var tanU1 = (1-f) * Math.tan(fi1 * Math.PI/180), cosU1 = 1 / Math.sqrt((1 + tanU1*tanU1)), sinU1 = tanU1 * cosU1;
var tanU2 = (1-f) * Math.tan(fi2 * Math.PI/180), cosU2 = 1 / Math.sqrt((1 + tanU2*tanU2)), sinU2 = tanU2 * cosU2;


var lambda = L;
var lambdap = L+1;

//console.log(tanU2);
var nit = 0;
var lambda = L, lambdap, iterationLimit = 100;
while (Math.abs(lambda-lambdap) > 1e-12) {
    var sinlambda = Math.sin(lambda), coslambda = Math.cos(lambda);
    var sinSqs = (cosU2*sinlambda) * (cosU2*sinlambda) + (cosU1*sinU2-sinU1*cosU2*coslambda) * (cosU1*sinU2-sinU1*cosU2*coslambda);
    var sins = Math.sqrt(sinSqs);
    if (sins==0) (console.log("co-incident points"));  // co-incident points
    var coss = sinU1*sinU2 + cosU1*cosU2*coslambda;
    var s = Math.atan2(sins, coss);
    var sina = cosU1 * cosU2 * sinlambda / sins;
    var cosSqa = 1 - sina*sina;
    var cos2sM = coss - 2*sinU1*sinU2/cosSqa;
    var C = f/16*cosSqa*(4+f*(4-3*cosSqa));
    lambdap = lambda;
    lambda = L + (1-C) * f * sina * (s + C*sins*(cos2sM+C*coss*(-1+2*cos2sM*cos2sM)));
    nit++;
    if (nit==1) break;
}

console.log(sinlambda);
console.log(coslambda);
console.log(sinSqs);
console.log(sins);
console.log(coss);
console.log(sina);
console.log(cosSqa);
console.log(cos2sM);
console.log(C);
console.log(lambda);
console.log(Math.sin(lambdap));

var uSq = cosSqa * (a*a - b*b) / (b*b);
var A = 1 + uSq/16384*(4096+uSq*(-768+uSq*(320-175*uSq)));
var B = uSq/1024 * (256+uSq*(-128+uSq*(74-47*uSq)));
var Ds = B*sins*(cos2sM+B/4*(coss*(-1+2*cos2sM*cos2sM)-B/6*cos2sM*(-3+4*sins*sins)*(-3+4*cos2sM*cos2sM)));

var s = b*A*(s-Ds);

var fwdAz = Math.atan2(cosU2*sinlambda,  cosU1*sinU2-sinU1*cosU2*coslambda);
var revAz = Math.atan2(cosU1*sinlambda, -sinU1*cosU2+cosU1*sinU2*coslambda);

//console.log(s);