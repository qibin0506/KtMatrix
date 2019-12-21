import java.lang.AssertionError
import java.lang.StringBuilder
import kotlin.math.min
import kotlin.math.pow

/** created by qibin*/
class Mat(
        val rows: Int,
        val cols: Int,
        init: (() -> Array<Number>)? = null) {

    private var data: Array<Array<Double>>

    /**
     * the transpose of Matrix.
     */
    val T: Mat
        get() {
            return this.transpose()
        }

    /**
     * the inverse of matrix.
     */
    val I: Mat
        get() {
            return this.inverse()
        }

    init {
        val arrayData = init?.invoke()
        assert(arrayData == null || this.rows * this.cols == arrayData.size, "data.size must be equals rows * cols")

        this.data = Array(this.rows) { row ->
            Array(this.cols) { col ->
                if (arrayData != null) arrayData[row * this.cols + col].toDouble() else 0.0
            }
        }
    }

    /**
     * the dot product of this and right.
     */
    fun dot(right: Mat): Mat {
        assert(this.cols == right.rows, "left.cols must be equals right.rows")
        val rst = Mat(this.rows, right.cols)

        for (i in 0 until this.rows) {
            for (j in 0 until this.cols) {
                for (k in 0 until right.cols) {
                    rst[i, k] += this[i, j] * right[j, k]
                }
            }
        }

        return rst
    }

    /**
     * the trace of this matrix.
     */
    fun trace(): Double {
        val minIdx = min(this.rows, this.cols)

        var sum = 0.0
        for (i in 0 until minIdx) {
            sum += this[i, i]
        }

        return sum
    }

    /**
     * the transpose of this matrix.
     */
    fun transpose(): Mat {
        val rst = Mat(cols, rows)
        for (i in 0 until this.rows) {
            for (j in 0 until this.cols) {
                rst[j, i] = this[i, j]
            }
        }
        return rst
    }

    /**
     * the determinant of this matrix.
     */
    fun det(): Double {
        assert(this.rows == this.cols, "this.cols must be equals this.rows")
        if (this.rows == 1) {
            return this[0, 0]
        }

        return det(this)
    }

    /**
     * the adjugate matrix of this matrix.
     */
    fun adj(): Mat {
        assert(this.rows == this.cols, "this.cols must be equals this.rows")
        val rst = Mat(this.rows, this.cols)

        val transpose = this.T

        for (i in 0 until this.rows) {
            for (j in 0 until this.cols) {
                rst[i, j] = (-1.0).pow(i + j) * det(minor(transpose, i, j))
            }
        }

        return rst
    }

    /**
     * the inverse of this matrix.
     */
    fun inverse(): Mat {
        assert(this.rows == this.cols, "this.cols must be equals this.rows")
        val det = this.det()
        if (det == 0.0) {
            throw Exception("determinant is zero")
        }

        val adj = this.adj()
        return adj / det
    }

    private fun det(mat: Mat): Double {
        if (mat.rows == 2) {
            return mat[0, 0] * mat[1, 1] - mat[0, 1] * mat[1, 0]
        }

        var rst = 0.0
        for (j in 0 until mat.cols) {
            rst += (-1.0).pow(j) * mat[0, j] * det(minor(mat, 0, j))
        }

        return rst
    }

    private fun minor(mat: Mat, row: Int, col: Int): Mat {
        val rst = Mat(mat.rows - 1, mat.cols - 1)
        for (i in 0 until mat.rows) {
            if (i == row) {
                continue
            }

            for (j in 0 until mat.cols) {
                if (j == col) {
                    continue
                }

                rst[if (i < row) i else i - 1, if (j < col) j else j - 1] = mat[i, j]
            }
        }

        return rst
    }

    operator fun get(rowsIndex: Int, colsIndex: Int): Double {
        return this.data[rowsIndex][colsIndex]
    }

    operator fun set(rowsIndex: Int, colsIndex: Int, value: Double) {
        this.data[rowsIndex][colsIndex] = value
    }

    operator fun plus(right: Mat): Mat {
        assert(this.rows == right.rows && this.cols == right.cols, "left.rows and left.cols must be equals right.rows right.cols")
        val rst = Mat(this.rows, this.cols)

        for (i in 0 until this.rows) {
            for (j in 0 until this.cols) {
                rst[i, j] = this[i, j] + right[i, j]
            }
        }

        return rst
    }

    operator fun plus(scalar: Double): Mat {
        val rst = Mat(this.rows, this.cols)

        for (i in 0 until this.rows) {
            for (j in 0 until this.cols) {
                rst[i, j] = this[i, j] + scalar
            }
        }

        return rst
    }

    operator fun minus(right: Mat): Mat {
        assert(this.rows == right.rows && this.cols == right.cols, "left.rows and left.cols must be equals right.rows right.cols")
        val rst = Mat(this.rows, this.cols)

        for (i in 0 until this.rows) {
            for (j in 0 until this.cols) {
                rst[i, j] = this[i, j] - right[i, j]
            }
        }

        return rst
    }

    operator fun minus(scalar: Double): Mat {
        val rst = Mat(this.rows, this.cols)

        for (i in 0 until this.rows) {
            for (j in 0 until this.cols) {
                rst[i, j] = this[i, j] - scalar
            }
        }

        return rst
    }

    operator fun times(right: Mat): Mat {
        assert(this.rows == right.rows && this.cols == right.cols, "left.rows and left.cols must be equals right.rows right.cols")
        val rst = Mat(this.rows, this.cols)

        for (i in 0 until this.rows) {
            for (j in 0 until this.cols) {
                rst[i, j] = this[i, j] * right[i, j]
            }
        }

        return rst
    }

    operator fun times(scalar: Double): Mat {
        val rst = Mat(this.rows, this.cols)

        for (i in 0 until this.rows) {
            for (j in 0 until this.cols) {
                rst[i, j] = this[i, j] * scalar
            }
        }

        return rst
    }

    operator fun div(right: Mat): Mat {
        assert(this.rows == right.rows && this.cols == right.cols, "left.rows and left.cols must be equals right.rows right.cols")
        val rst = Mat(this.rows, this.cols)

        for (i in 0 until this.rows) {
            for (j in 0 until this.cols) {
                assert(right[i, j] != 0.0, "div by zero exception")
                rst[i, j] = this[i, j] / right[i, j]
            }
        }

        return rst
    }

    operator fun div(scalar: Double): Mat {
        assert(scalar != 0.0, "div by zero exception")
        val rst = Mat(this.rows, this.cols)

        for (i in 0 until this.rows) {
            for (j in 0 until this.cols) {
                rst[i, j] = this[i, j] / scalar
            }
        }

        return rst
    }

    override fun toString(): String {
        val builder = StringBuilder()
        builder.append('\n').append('[')
        for (i in 0 until this.rows) {
            builder.append('[')
            for (j in 0 until this.cols) {
                builder.append(this[i, j])
                if (j != this.cols - 1) {
                    builder.append(',')
                    builder.append(' ')
                }
            }

            builder.append(']')
            if (i != this.rows - 1) {
                builder.append(',')
                builder.append('\n')
            }
        }

        builder.append(']')
        return builder.toString()
    }
}

private fun assert(value: Boolean, msg: String) {
    if (!value) {
        throw AssertionError(msg)
    }
}

fun identity(size: Int): Mat {
    val rst = Mat(size, size)
    for (i in 0 until size) {
        rst[i, i] = 1.0
    }

    return rst
}
