# KtMatrix
A kotlin implemention of Matrix

## Usage

1. init
get a 3x3 matrix and initialize the value.
``` kotlin
val mat = Mat(3, 3) {
    arrayOf(
            1, 2, 3,
            1, 0, -1,
            0, 1, 1
    )
}
```

or, get a 3x3 identity matrix by using,
``` kotlin
val mat = identity(2)
```

2. get value
get the value at position (i, j)
``` kotlin
println(mat[i, j])
```
or, dump the full matrix by using,
```kotlin
print(mat)
```

3. set value
set the value at position (i, j)
``` kotlin
mat[i, j] = 10.0
```

4. transpose
``` kotlin
println(mat.T)
```

5. inverse
``` kotlin
println(mat.I)
```

6. addition
``` kotlin
println(mat + mat2)
println(mat + 2.0)
```

7. minus
``` kotlin
println(mat - mat2)
println(mat - 2.0)
```

8. element-wise product
``` kotlin
println(mat * mat2)
println(mat * 2.0)
```

9. dot product
``` kotlin
println(mat.dot(mat2))
```

10. element-wise div
``` kotlin
println(mat / mat2)
println(mat / 2.0)
```

11. determinant
``` kotlin
println(mat.det())
```

12. trace
``` kotlin
println(mat.trace())
```

13. adjugate matrix
``` kotlin
println(mat.adj())
```
