 
/**
 * https://www.khanacademy.org/computing/computer-science/cryptography/random-algorithms-probability/pi/level-10-fermat-primality-test
 * 
 */


background(0, 0, 0);
 /** 
 * Primality Test (Trial Division vs. Fermat's Test)
 * Goal: Compare leading trial division tests with
 * Fermat primality test
 */
 // NUMBER TO TEST
 var numtest = 13412;
 // Carmichal Numbers: 561 , 1105  , 8911
 // Big Primes: 44721359 , 54734431
 
 // ERROR (number of iterations)
 var numTrials = 20;
 
 // Algorithm A step counter
 var Asteps = 1;
 // Algorithm B step counter
 var Bsteps = 1;

  /**
 * Fast Modular Exponentiation
 * Input: factor, power, modulus
 * Output: factor^power % modulus
 */ 
 var fastermod = function(factor,power,modulus){
    var result = 1;
    while(power > 0){
        if (power % 2 === 1){
            result = (result*factor) % modulus;
            power = power-1;
        }
    power = power/2;
    factor = (factor*factor)%modulus;
    }
    return result;
};

 /**
 * GCD (greatest common divisor)
 * Input: two integers (x,y)
 * Output: greatest common divisor of x & y
 */ 
var gcd = function(x, y){
    while (y !== 0) {
        var z = x % y;
		x = y;
		y = z;
	}
	return x;
};

  /**
 * Fermat Primality Test
 * Input: a single integer we want to test (numtest)
 * Output: TRUE if prime, FALSE if composite
 * 
 */ 
 var isPrimeA = function(inputNum){
    // step counter
    Asteps += 1;
    
    // run through numTrials
    for (var trial = 0; trial < numTrials; trial++){
        // increment step counter
        Asteps += 6;

        // generate a between 1 and inputNum - 1
        var randTest = floor((random()*(inputNum-2)))+2;

        // check if common factor exists
        if (gcd(randTest,inputNum) !== 1){
        // factor was found, therefore composite
        return false;
        }
        
        // fermat test
       if (fastermod(randTest,inputNum-1,inputNum)!== 1){
       // must be composite
        return false;
        }
        
    } // end for loop
    
    return true;
 };

  /**
 * Trial Division 
 * Input: a single integer we want to test 
 * Output: TRUE if prime, FALSE if composite
 * 
 * ABOUT: for more information see Level 3: 
 * https://www.khanacademy.org/math/applied-math/cryptography/comp-number-theory/p/level-3-challenge
 */ 
 var isPrimeB=function(inputNum){
     Bsteps = Bsteps+3;
     // assume inputNum is prime
     var primeCheck = true;
    // check if input num is divisible by 2
    if (inputNum % 2 === 0) {
    // if 2 return true (prime) otherwise return false 
        return (inputNum === 2);
    }
    var upperBound = floor(sqrt(inputNum));
    // loop until test <= square root of inputNum
    for(var test = 3; test <= upperBound; test += 2){
        Bsteps = Bsteps+3;
    // check if test divides inputNum
        if (inputNum % test === 0){
        // found a factor!
        return false;
        }
    
    } 
    // Didn't find factor, therfore return true 
    return true;
 };
 


 
// ***********DISPLAY***************** 

// ALGORITHM A
textSize(14);
fill (255, 238, 0);
text("Fermat Test",80,13);
fill (255, 255, 255);
textSize(16);
text(numtest+" is ",34,35);
// if prime output prime
if (isPrimeA(numtest) === true){
fill (22, 173, 224);
text("prime", 91, 55);    
}
// or else composite
else{
fill (222, 22, 22);
text("composite", 77, 55);    
}

// ALGORITHM B
textSize(14);
fill (255, 238, 0);
text("Trial Division",231,13);
// output the number chosen + "is"
textSize(16);
fill(255, 255, 255);
text(numtest+" is ",205,35);

// if prime output prime
if (isPrimeB(numtest) === true){
fill (22, 173, 224);
text("prime", 261, 54);    
}
// or else output composite
else{
fill (222, 22, 22);
text("composite", 261, 54);    
}


// ***********END DISPLAY***************** 

// ***********Complexity Visualization***************** 

textSize(13);
// Algorithm A
fill(1+Asteps/5000, 100, 100);
rect(35,390 - Asteps/10000,150,1000);
fill(255, 247, 0);
text("# Steps: "+Asteps,48,389);

// Algorithm B
fill(1+Bsteps/10000, 100, 100);
rect(215,392 - Bsteps/10000,150,1000);
fill(255, 247, 0);
text("# Steps: "+Bsteps,239,388);


stroke(230, 51, 51);
strokeWeight(10);
line(0,81,462,81);
fill(255, 255, 255);
text("STEP LIMIT!",155,86);

// ***********END Complexity Visualization***************** 