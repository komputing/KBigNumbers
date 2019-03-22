package kbignumbers.bigdecimal

import kbignumbers.biginteger.BigInteger

/**
 * Immutable arbitrary-precision integers.  All operations behave as if
 * BigDecimals were represented in two's-complement notation.
 * BigDecimal provides analogues to all of Kotlin's
 * primitive integer operators, and all relevant methods from java.lang.Math.
 * Additionally, BigDecimal provides operations for modular arithmetic, GCD
 * calculation, primality testing, prime generation, bit manipulation,
 * and a few other miscellaneous operations.
 *
 * <p>Semantics of arithmetic operations exactly mimic those of Kotlin's integer
 * arithmetic operators, as defined in <i>The Kotlin Language Specification</i>.
 * For example, division by zero throws an {@code ArithmeticException}, and
 * division of a negative by a positive yields a negative (or zero) remainder.
 * All of the details in the Spec concerning overflow are ignored, as
 * BigDecimals are made as large as necessary to accommodate the results of an
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
 * operations can produce a BigDecimal with a different sign from the
 * BigDecimal being operated on, as they affect only a single bit, and the
 * "infinite word size" abstraction provided by this class ensures that there
 * are infinitely many "virtual sign bits" preceding each BigDecimal.
 *
 * <p>For the sake of brevity and clarity, pseudo-code is used throughout the
 * descriptions of BigDecimal methods.  The pseudo-code expression
 * {@code (i + j)} is shorthand for "a BigDecimal whose value is
 * that of the BigDecimal {@code i} plus that of the BigDecimal {@code j}."
 * The pseudo-code expression {@code (i == j)} is shorthand for
 * "{@code true} if and only if the BigDecimal {@code i} represents the same
 * value as the BigDecimal {@code j}."  Other pseudo-code expressions are
 * interpreted similarly.
 *
 * <p>All methods and constructors in this class throw
 * {@code NullPointerException} when passed
 * a null object reference for any input parameter.
 *
 * BigDecimal must support values in the range
 * -2<sup>{@code Integer.MAX_VALUE}</sup> (exclusive) to
 * +2<sup>{@code Integer.MAX_VALUE}</sup> (exclusive)
 * and may support values outside of that range.
 *
 * The range of probable prime values is limited and may be less than
 * the full supported positive range of {@code BigDecimal}.
 * The range must be at least 1 to 2<sup>500000000</sup>.
 *
 * @implNote
 * BigDecimal constructors and operations throw {@code ArithmeticException} when
 * the result is out of the supported range of
 * -2<sup>{@code Integer.MAX_VALUE}</sup> (exclusive) to
 * +2<sup>{@code Integer.MAX_VALUE}</sup> (exclusive).
 */
actual class BigDecimal(val value: java.math.BigDecimal) : Number(), Comparable<BigDecimal> {

    /**
     * Translates the decimal String representation of a BigDecimal into a BigDecimal.
     */
    actual constructor(value: String) : this(java.math.BigDecimal(value))

    actual constructor(value: BigInteger) : this(java.math.BigDecimal(value.value))

    /**
     * Returns a BigDecimal whose value is {@code (this + value)}.
     *
     * @param `value` value to be added to this BigDecimal.
     * @return {@code this + value}
     */
    actual fun add(value: BigDecimal): BigDecimal {
        return BigDecimal(this.value.add(value.value))
    }

    /**
     * Returns a BigDecimal whose value is {@code (this - val)}.
     *
     * @param value value to be subtracted from this BigDecimal.
     * @return {@code this - val}
     */
    actual fun subtract(value: BigDecimal): BigDecimal {
        return BigDecimal(this.value.subtract(value.value))
    }

    /**
     * Returns a BigDecimal whose value is {@code (this * val)}.
     *
     * @param value value to be multiplied by this BigDecimal.
     * @return {@code this * val}
     */
    actual fun multiply(value: BigDecimal): BigDecimal {
        return BigDecimal(this.value.multiply(value.value))
    }

    /**
     * Returns a {@code BigDecimal} whose value is {@code (this /
     * divisor)}, and whose preferred scale is {@code (this.scale() -
     * divisor.scale())}; if the exact quotient cannot be
     * represented (because it has a non-terminating decimal
     * expansion) an {@code ArithmeticException} is thrown.
     *
     * @param value value by which this {@code BigDecimal} is to be divided.
     * @throws ArithmeticException if the exact quotient does not have a
     *         terminating decimal expansion
     */
    actual fun divide(value: BigDecimal): BigDecimal {
        return BigDecimal(this.value.divide(value.value))
    }

    /**
     * Returns a BigDecimal whose value is {@code (this % val)}.
     *
     * @param value value by which this BigDecimal is to be divided, and the
     *         remainder computed.
     * @return {@code this % val}
     * @throws ArithmeticException if {@code val} is zero.
     */
    actual fun remainder(value: BigDecimal): BigDecimal {
        return BigDecimal(this.value.remainder(value.value))
    }

    /**
     * Returns the signum function of this BigDecimal.
     *
     * @return -1, 0 or 1 as the value of this BigDecimal is negative, zero or
     *         positive.
     */
    actual fun signum(): Int {
        return this.value.signum()
    }

    /**
     * Returns the string representation of this {@code BigDecimal},
     * using scientific notation if an exponent is needed.
     *
     * <p>A standard canonical string form of the {@code BigDecimal}
     * is created as though by the following steps: first, the
     * absolute value of the unscaled value of the {@code BigDecimal}
     * is converted to a string in base ten using the characters
     * {@code '0'} through {@code '9'} with no leading zeros (except
     * if its value is zero, in which case a single {@code '0'}
     * character is used).
     *
     * <p>Next, an <i>adjusted exponent</i> is calculated; this is the
     * negated scale, plus the number of characters in the converted
     * unscaled value, less one.  That is,
     * {@code -scale+(ulength-1)}, where {@code ulength} is the
     * length of the absolute value of the unscaled value in decimal
     * digits (its <i>precision</i>).
     *
     * <p>If the scale is greater than or equal to zero and the
     * adjusted exponent is greater than or equal to {@code -6}, the
     * number will be converted to a character form without using
     * exponential notation.  In this case, if the scale is zero then
     * no decimal point is added and if the scale is positive a
     * decimal point will be inserted with the scale specifying the
     * number of characters to the right of the decimal point.
     * {@code '0'} characters are added to the left of the converted
     * unscaled value as necessary.  If no character precedes the
     * decimal point after this insertion then a conventional
     * {@code '0'} character is prefixed.
     *
     * <p>Otherwise (that is, if the scale is negative, or the
     * adjusted exponent is less than {@code -6}), the number will be
     * converted to a character form using exponential notation.  In
     * this case, if the converted {@code BigInteger} has more than
     * one digit a decimal point is inserted after the first digit.
     * An exponent in character form is then suffixed to the converted
     * unscaled value (perhaps with inserted decimal point); this
     * comprises the letter {@code 'E'} followed immediately by the
     * adjusted exponent converted to a character form.  The latter is
     * in base ten, using the characters {@code '0'} through
     * {@code '9'} with no leading zeros, and is always prefixed by a
     * sign character {@code '-'} (<tt>'&#92;u002D'</tt>) if the
     * adjusted exponent is negative, {@code '+'}
     * (<tt>'&#92;u002B'</tt>) otherwise).
     *
     * <p>Finally, the entire string is prefixed by a minus sign
     * character {@code '-'} (<tt>'&#92;u002D'</tt>) if the unscaled
     * value is less than zero.  No sign character is prefixed if the
     * unscaled value is zero or positive.
     *
     * <p><b>Examples:</b>
     * <p>For each representation [<i>unscaled value</i>, <i>scale</i>]
     * on the left, the resulting string is shown on the right.
     * <pre>
     * [123,0]      "123"
     * [-123,0]     "-123"
     * [123,-1]     "1.23E+3"
     * [123,-3]     "1.23E+5"
     * [123,1]      "12.3"
     * [123,5]      "0.00123"
     * [123,10]     "1.23E-8"
     * [-123,12]    "-1.23E-10"
     * </pre>
     *
     * <b>Notes:</b>
     * <ol>
     *
     * <li>There is a one-to-one mapping between the distinguishable
     * {@code BigDecimal} values and the result of this conversion.
     * That is, every distinguishable {@code BigDecimal} value
     * (unscaled value and scale) has a unique string representation
     * as a result of using {@code toString}.  If that string
     * representation is converted back to a {@code BigDecimal} using
     * the {@link #BigDecimal(String)} constructor, then the original
     * value will be recovered.
     *
     * <li>The string produced for a given number is always the same;
     * it is not affected by locale.  This means that it can be used
     * as a canonical string representation for exchanging decimal
     * data, or as a key for a Hashtable, etc.  Locale-sensitive
     * number formatting and parsing is handled by the {@link
     * java.text.NumberFormat} class and its subclasses.
     *
     * <li>The {@link #toEngineeringString} method may be used for
     * presenting numbers with exponents in engineering notation, and the
     * {@link #setScale(int,RoundingMode) setScale} method may be used for
     * rounding a {@code BigDecimal} so it has a known number of digits after
     * the decimal point.
     *
     * <li>The digit-to-character mapping provided by
     * {@code Character.forDigit} is used.
     *
     * </ol>
     *
     * @return string representation of this {@code BigDecimal}.
     */
    actual override fun toString(): String {
        return this.value.toString()
    }

    /**
     * Converts this {@code BigDecimal} to a {@code byte}, checking
     * for lost information.  If the value of this {@code BigDecimal}
     * is out of the range of the {@code byte} type, then an
     * {@code ArithmeticException} is thrown.
     *
     * @return this {@code BigDecimal} converted to a {@code byte}.
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
        return other is BigDecimal && this.value == other.value
    }

    override fun compareTo(other: BigDecimal): Int {
        return value.compareTo(other.value)
    }

    actual companion object {
        actual val ZERO: BigDecimal = BigDecimal(java.math.BigDecimal.ZERO)
        actual val ONE: BigDecimal = BigDecimal(java.math.BigDecimal.ONE)
        actual val TEN: BigDecimal = BigDecimal(java.math.BigDecimal.TEN)
        actual fun valueOf(value: Long): BigDecimal {
            return BigDecimal(java.math.BigDecimal.valueOf(value))
        }
    }
}
