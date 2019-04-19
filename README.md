# KBigNumbers

[Kotlin multiplatform](https://kotlinlang.org/docs/reference/multiplatform.html) porting of Java's 
[BigInteger](https://docs.oracle.com/javase/7/docs/api/java/math/BigInteger.html) and 
[BigDecimal](https://docs.oracle.com/javase/7/docs/api/java/math/BigDecimal.html).

Currently this library is only compatible with `common` and `jvm` modules.  
Any PR implementing is platforms are more than welcome. 

## Packages
The project is split into two different packages: `biginteger` and `bigdecimal` (which depends on `biginteger`).

The library is available on Jitpack. In order to get it, you have to do as follows.

**1.** Add the Jitpack repository to your project `build.gradle` file. 
```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

**2.** Add the dependency you prefer. All the dependencies can be used as follows
```groovy
dependencies {
    "com.github.komputing.kbignumbers:{module}-{platform}:{version}"
}
```

Examples:
```groovy
dependencies {
    "com.github.komputing.kbignumbers:biginteger-common:1.0.0-RC1"
    "com.github.komputing.kbignumbers:bigdecimal-jvm:1.0.0-RC1"
}
```

### Available modules
| Module | Description |
| :----- | :---------- |
| `biginteger` | Contains the `BigInteger` class definition | 
| `bigdecimal` | Contains the `BigDecimal class definition | 


### Available platforms 
| Platform | Description | 
| :------- | :---------- |
| `common` | Kotlin multiplatform | 
| `jvm` | JVM-based platform |

### Latest version
The latest version is [![](https://jitpack.io/v/komputing/KBigNumbers.svg)](https://jitpack.io/#komputing/KBigNumbers)

## Current status 
### BigInteger
#### Constructors
| Constructor | Status |
| :----- | :----: |
| `BigInteger(byte[] val)` | ✗ | 
| `BigInteger(int signum, byte[] magnitude)` | ✓ | 
| `BigInteger(int bitLength, int certainty, Random rnd)` | ✗ | 
| `BigInteger(int numBits, Random rnd)` | ✗ | 
| `BigInteger(String val)` | ✓ | 
| `BigInteger(String val, int radix)` | ✓ |

#### Methods
| Method | Status |
| :----- | :----: |
| `abs()` | ✗ | 
| `add(BigInteger val)` | ✓ | 
| `and(BigInteger val)` | ✓ | 
| `andNot(BigInteger val)` | ✗ | 
| `bitCount()` | ✗ | 
| `bitLength()` | ✗ | 
| `clearBit(int n)` | ✗ | 
| `compareTo(BigInteger val)` | ✓ | 
| `divide(BigInteger val)` | ✓ | 
| `divideAndRemainder(BigInteger val)` | ✗ | 
| `doubleValue()` | ✓ | 
| `equals(Object x)` | ✓ | 
| `flipBit(int n)` | ✗ | 
| `floatValue()` | ✓ | 
| `gcd(BigInteger val)` | ✗ | 
| `getLowestSetBit()` | ✗ | 
| `hashCode()` | ✗ | 
| `intValue()` | ✓ | 
| `isProbablePrime(int certainty)` | ✗ | 
| `longValue()` | ✓ | 
| `max(BigInteger val)` | ✗ | 
| `min(BigInteger val)` | ✗ | 
| `mod(BigInteger m)` | ✓ | 
| `modInverse(BigInteger m)` | ✗ | 
| `modPow(BigInteger exponent, BigInteger m)` | ✗ | 
| `multiply(BigInteger val)` | ✓ | 
| `negate()` | ✗ | 
| `nextProbablePrime()` | ✗ | 
| `not()` | ✗ | 
| `or(BigInteger val)` | ✗ | 
| `pow(int exponent)` | ✗ | 
| `probablePrime(int bitLength, Random rnd)` | ✗ | 
| `remainder(BigInteger val)` | ✓ | 
| `setBit(int n)` | ✗ | 
| `shiftLeft(int n)` | ✓ | 
| `shiftRight(int n)` | ✓ | 
| `signum()` | ✓ | 
| `subtract(BigInteger val)` | ✗ | 
| `testBit(int n)` | ✗ | 
| `toByteArray()` | ✓ | 
| `toString()` | ✗ | 
| `toString(int radix)` | ✓ | 
| `valueOf(long val)` | ✗ | 
| `xor(BigInteger val)` | ✓ | 


### BigDecimal
#### Constructors
| Constructor | Status |
| :----- | :----: |
|`BigDecimal(BigInteger val)` | ✓ |
|`BigDecimal(BigInteger unscaledVal, int scale)` | ✗ |
|`BigDecimal(BigInteger unscaledVal, int scale, MathContext mc)` | ✗ |
|`BigDecimal(BigInteger val, MathContext mc)` | ✗ |
|`BigDecimal(char[] in)` | ✗ |
|`BigDecimal(char[] in, int offset, int len)` | ✗ |
|`BigDecimal(char[] in, int offset, int len, MathContext mc)` | ✗ |
|`BigDecimal(char[] in, MathContext mc)` | ✗ |
|`BigDecimal(double val)` | ✗ |
|`BigDecimal(double val, MathContext mc)` | ✗ |
|`BigDecimal(int val)` | ✗ |
|`BigDecimal(int val, MathContext mc)` | ✗ |
|`BigDecimal(long val)` | ✗ |
|`BigDecimal(long val, MathContext mc)` | ✗ |
|`BigDecimal(String val)` | ✓ |
|`BigDecimal(String val, MathContext mc)` | ✗ |

#### Methods
| Method | Status |
| :----- | :----: |
| `abs()` | ✗ |
| `abs(MathContext mc)` | ✗ |
| `add(BigDecimal augend)` | ✓ |
| `add(BigDecimal augend, MathContext mc)` | ✗ |
| `byteValueExact()` | ✓ |
| `compareTo(BigDecimal val)` | ✓ |
| `divide(BigDecimal divisor)` | ✓ |
| `divide(BigDecimal divisor, int roundingMode)` | ✗ |
| `divide(BigDecimal divisor, int scale, int roundingMode)` | ✗ |
| `divide(BigDecimal divisor, int scale, RoundingMode roundingMode)` | ✗ |
| `divide(BigDecimal divisor, MathContext mc)` | ✗ |
| `divide(BigDecimal divisor, RoundingMode roundingMode)` | ✗ |
| `divideAndRemainder(BigDecimal divisor)` | ✗ |
| `divideAndRemainder(BigDecimal divisor, MathContext mc)` |✗  |
| `divideToIntegralValue(BigDecimal divisor)` | ✗ |
| `divideToIntegralValue(BigDecimal divisor, MathContext mc)` | ✗ |
| `doubleValue()` | ✓ |
| `equals(Object x)` | ✓ |
| `floatValue()` | ✓ |
| `hashCode()` | ✗ |
| `intValue()` | ✗ |
| `intValueExact()` | ✗ |
| `longValue()` | ✓ |
| `longValueExact()` | ✗ |
| `max(BigDecimal val)` | ✗ |
| `min(BigDecimal val)` | ✗ |
| `movePointLeft(int n)` | ✗ |
| `movePointRight(int n)` | ✗ |
| `multiply(BigDecimal multiplicand)` | ✗ |
| `multiply(BigDecimal multiplicand, MathContext mc)` | ✗ |
| `negate()` | ✗ |
| `negate(MathContext mc)` | ✗ |
| `plus()` | ✗ |
| `plus(MathContext mc)` | ✗ |
| `pow(int n)` | ✗ |
| `pow(int n, MathContext mc)` | ✗ |
| `precision()` | ✗ |
| `remainder(BigDecimal divisor)` | ✗ |
| `remainder(BigDecimal divisor, MathContext mc)` | ✗ |
| `round(MathContext mc)` | ✗ |
| `scale()` | ✗ |
| `scaleByPowerOfTen(int n)` | ✗ |
| `setScale(int newScale)` | ✗ |
| `setScale(int newScale, int roundingMode)` | ✗ |
| `setScale(int newScale, RoundingMode roundingMode)` | ✗ |
| `shortValueExact()` | ✗ |
| `signum()` | ✓ |
| `stripTrailingZeros()` | ✗ |
| `subtract(BigDecimal subtrahend)` | ✗ |
| `subtract(BigDecimal subtrahend, MathContext mc)` | ✗ |
| `toBigInteger()` | ✗ |
| `toBigIntegerExact()` | ✗ |
| `toEngineeringString()` | ✗ |
| `toPlainString()` |✗  |
| `toString()` | ✓ |
| `ulp()` | ✗ |
| `unscaledValue()` | ✗ |
| `valueOf(double val)` | ✗ |
| `valueOf(long val)` | ✗ |
| `valueOf(long unscaledVal, int scale)` | ✗ |
