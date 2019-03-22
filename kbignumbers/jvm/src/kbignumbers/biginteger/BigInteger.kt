package kbignumbers.biginteger

/**
 * Immutable arbitrary-precision integers.  All operations behave as if
 * BigIntegers were represented in two's-complement notation.
 * BigInteger provides analogues to all of Kotlin's
 * primitive integer operators, and all relevant methods from java.lang.Math.
 * Additionally, BigInteger provides operations for modular arithmetic, GCD
 * calculation, primality testing, prime generation, bit manipulation,
 * and a few other miscellaneous operations.
 *
 * <p>Semantics of arithmetic operations exactly mimic those of Kotlin's integer
 * arithmetic operators, as defined in <i>The Kotlin Language Specification</i>.
 * For example, division by zero throws an {@code ArithmeticException}, and
 * division of a negative by a positive yields a negative (or zero) remainder.
 * All of the details in the Spec concerning overflow are ignored, as
 * BigIntegers are made as large as necessary to accommodate the results of an
 * operation.
 *
 * <p>Semantics of shift operations extend those of Kotlin's shift operators
 * to allow for negative shift distances.  A right-shift with a negative
 * shift distance results in a left shift, and vice-versa.  The unsigned
 * right shift operator ({@code >>>}) is omitted, as this operation makes
 * little sense in combination with the "infinite word size" abstraction
 * provided by this class.
 *
 * <p>Semantics of bitwise logical operations exactly mimic those of Kotlin's
 * bitwise integer operators.  The binary operators ({@code and},
 * {@code or}, {@code xor}) implicitly perform sign extension on the shorter
 * of the two operands prior to performing the operation.
 *
 * <p>Comparison operations perform signed integer comparisons, analogous to
 * those performed by Kotlin's relational and equality operators.
 *
 * <p>Modular arithmetic operations are provided to compute residues, perform
 * exponentiation, and compute multiplicative inverses.  These methods always
 * return a non-negative result, between {@code 0} and {@code (modulus - 1)},
 * inclusive.
 *
 * <p>Bit operations operate on a single bit of the two's-complement
 * representation of their operand.  If necessary, the operand is sign-
 * extended so that it contains the designated bit.  None of the single-bit
 * operations can produce a BigInteger with a different sign from the
 * BigInteger being operated on, as they affect only a single bit, and the
 * "infinite word size" abstraction provided by this class ensures that there
 * are infinitely many "virtual sign bits" preceding each BigInteger.
 *
 * <p>For the sake of brevity and clarity, pseudo-code is used throughout the
 * descriptions of BigInteger methods.  The pseudo-code expression
 * {@code (i + j)} is shorthand for "a BigInteger whose value is
 * that of the BigInteger {@code i} plus that of the BigInteger {@code j}."
 * The pseudo-code expression {@code (i == j)} is shorthand for
 * "{@code true} if and only if the BigInteger {@code i} represents the same
 * value as the BigInteger {@code j}."  Other pseudo-code expressions are
 * interpreted similarly.
 *
 * <p>All methods and constructors in this class throw
 * {@code NullPointerException} when passed
 * a null object reference for any input parameter.
 *
 * BigInteger must support values in the range
 * -2<sup>{@code Integer.MAX_VALUE}</sup> (exclusive) to
 * +2<sup>{@code Integer.MAX_VALUE}</sup> (exclusive)
 * and may support values outside of that range.
 *
 * The range of probable prime values is limited and may be less than
 * the full supported positive range of {@code BigInteger}.
 * The range must be at least 1 to 2<sup>500000000</sup>.
 *
 * @implNote
 * BigInteger constructors and operations throw {@code ArithmeticException} when
 * the result is out of the supported range of
 * -2<sup>{@code Integer.MAX_VALUE}</sup> (exclusive) to
 * +2<sup>{@code Integer.MAX_VALUE}</sup> (exclusive).
 */
actual class BigInteger(val value: java.math.BigInteger) : Number(), Comparable<kbignumbers.biginteger.BigInteger> {

    /**
     * Translates the sign-magnitude representation of a BigInteger into a BigInteger.
     */
    actual constructor(signum: Int, magnitude: ByteArray) : this(java.math.BigInteger(signum, magnitude))

    /**
     * Translates the decimal String representation of a BigInteger into a BigInteger.
     */
    actual constructor(value: String) : this(java.math.BigInteger(value))

    /**
     * Translates the String representation of a BigInteger in the specified radix into a BigInteger.
     */
    actual constructor(value: String, radix: Int) : this(java.math.BigInteger(value, radix))

    /**
     * Returns a BigInteger whose value is {@code (this + value)}.
     *
     * @param `value` value to be added to this BigInteger.
     * @return {@code this + value}
     */
    actual fun add(value: BigInteger): BigInteger {
        return BigInteger(this.value.add(value.value))
    }

    /**
     * Returns a BigInteger whose value is {@code (this - val)}.
     *
     * @param value value to be subtracted from this BigInteger.
     * @return {@code this - val}
     */
    actual fun subtract(value: BigInteger): BigInteger {
        return BigInteger(this.value.subtract(value.value))
    }

    /**
     * Returns a BigInteger whose value is {@code (this * val)}.
     *
     * @param value value to be multiplied by this BigInteger.
     * @return {@code this * val}
     */
    actual fun multiply(value: BigInteger): BigInteger {
        return BigInteger(this.value.multiply(value.value))
    }

    /**
     * Returns a BigInteger whose value is {@code (this / val)}.
     *
     * @param value value by which this BigInteger is to be divided.
     * @return {@code this / val}
     * @throws ArithmeticException if {@code val} is zero.
     */
    actual fun divide(value: BigInteger): BigInteger {
        return BigInteger(this.value.divide(value.value))
    }

    /**
     * Returns a BigInteger whose value is {@code (this mod m}).  This method
     * differs from {@code remainder} in that it always returns a
     * <i>non-negative</i> BigInteger.
     *
     * @param m the modulus.
     * @return {@code this mod m}
     * @throws ArithmeticException {@code m} &le; 0
     * @see remainder
     */
    actual fun mod(m: BigInteger): BigInteger {
        return BigInteger(this.value.mod(m.value))
    }

    /**
     * Returns a BigInteger whose value is {@code (this % val)}.
     *
     * @param value value by which this BigInteger is to be divided, and the
     *         remainder computed.
     * @return {@code this % val}
     * @throws ArithmeticException if {@code val} is zero.
     */
    actual fun remainder(value: BigInteger): BigInteger {
        return BigInteger(this.value.remainder(value.value))
    }

    /**
     * Returns a BigInteger whose value is {@code (this ^ val)}.  (This method
     * returns a negative BigInteger if and only if exactly one of this and
     * value are negative.)
     *
     * @param value value to be XOR'ed with this BigInteger.
     * @return {@code this ^ val}
     */
    actual fun xor(value: BigInteger): BigInteger {
        return BigInteger(this.value.xor(value.value))
    }

    /**
     * Returns a BigInteger whose value is {@code (this & val)}.  (This
     * method returns a negative BigInteger if and only if this and val are
     * both negative.)
     *
     * @param value value to be AND'ed with this BigInteger.
     * @return {@code this & val}
     */
    actual fun and(value: BigInteger): BigInteger {
        return BigInteger(this.value.and(value.value))
    }

    /**
     * Returns a BigInteger whose value is {@code (this << n)}.
     * The shift distance, {@code n}, may be negative, in which case
     * this method performs a right shift.
     * (Computes <tt>floor(this * 2<sup>n</sup>)</tt>.)
     *
     * @param n shift distance, in bits.
     * @return {@code this << n}
     * @see shiftRight
     */
    actual fun shiftLeft(n: Int): BigInteger {
        return BigInteger(this.value.shiftLeft(n))
    }

    /**
     * Returns a BigInteger whose value is {@code (this >> n)}.  Sign
     * extension is performed.  The shift distance, {@code n}, may be
     * negative, in which case this method performs a left shift.
     * (Computes <tt>floor(this / 2<sup>n</sup>)</tt>.)
     *
     * @param n shift distance, in bits.
     * @return {@code this >> n}
     * @see shiftLeft
     */
    actual fun shiftRight(n: Int): BigInteger {
        return BigInteger(this.value.shiftRight(n))
    }

    /**
     * Returns the signum function of this BigInteger.
     *
     * @return -1, 0 or 1 as the value of this BigInteger is negative, zero or
     *         positive.
     */
    actual fun signum(): Int {
        return this.value.signum()
    }

    /**
     * Returns the decimal String representation of this BigInteger.
     * The digit-to-character mapping is used, and a minus sign is
     * prepended if appropriate.  (This representation is compatible
     * with the {@link #BigInteger(String) (String)} constructor, and
     * allows for String concatenation with Java's + operator.)
     *
     * @return decimal String representation of this BigInteger.
     */
    actual fun toString(radix: Int): String {
        return this.value.toString(radix)
    }

    /**
     * Returns a byte array containing the two's-complement
     * representation of this BigInteger.  The byte array will be in
     * <i>big-endian</i> byte-order: the most significant byte is in
     * the zeroth element.  The array will contain the minimum number
     * of bytes required to represent this BigInteger, including at
     * least one sign bit, which is {@code (ceil((this.bitLength() +
     * 1)/8))}.  (This representation is compatible with the
     * {@link #BigInteger(byte[]) (byte[])} constructor.)
     *
     * @return a byte array containing the two's-complement representation of
     *         this BigInteger.
     * @see #BigInteger(byte[])
     */
    actual fun toByteArray(): ByteArray {
        return this.value.toByteArray()
    }

    /**
     * Converts this {@code BigInteger} to a {@code byte}, checking
     * for lost information.  If the value of this {@code BigInteger}
     * is out of the range of the {@code byte} type, then an
     * {@code ArithmeticException} is thrown.
     *
     * @return this {@code BigInteger} converted to a {@code byte}.
     * @throws ArithmeticException if the value of {@code this} will
     * not exactly fit in a {@code byte}.
     */
    actual fun byteValueExact(): Byte {
        return this.value.byteValueExact()
    }

    override fun toByte(): Byte {
        return this.value.toByte()
    }

    override fun toChar(): Char {
        return this.value.toChar()
    }

    override fun toDouble(): Double {
        return this.value.toDouble()
    }

    override fun toFloat(): Float {
        return this.value.toFloat()
    }

    override fun toInt(): Int {
        return this.value.toInt()
    }

    override fun toLong(): Long {
        return this.value.toLong()
    }

    override fun toShort(): Short {
        return this.value.toShort()
    }

    override fun equals(other: Any?): Boolean {
        return other is BigInteger && this.value == other.value
    }

    override fun compareTo(other: BigInteger): Int {
        return value.compareTo(other.value)
    }

    actual companion object {
        actual val ZERO: BigInteger = BigInteger(java.math.BigInteger.ZERO)
        actual val ONE: BigInteger = BigInteger(java.math.BigInteger.ONE)
        actual val TEN: BigInteger = BigInteger(java.math.BigInteger.TEN)
        actual fun valueOf(value: Long): BigInteger {
            return BigInteger(java.math.BigInteger.valueOf(value))
        }
    }
}
