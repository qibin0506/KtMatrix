# KtMatrix
一个纯kotlin实现的矩阵工具，支持加减乘除，点乘，转置，求逆矩阵，求行列式，迹运算，求伴随矩阵等常用矩阵操作。

## 使用说明 [English version](https://github.com/qibin0506/KtMatrix/blob/master/README.md)

1. 初始化
初始化一个3x3的矩阵
``` kotlin
val mat = Mat(3, 3) {
    arrayOf(
            1, 2, 3,
            1, 0, -1,
            0, 1, 1
    )
}
```
或者利用一下代码获取一个3x3的单位矩阵
``` kotlin
val mat = identity(2)
```

2. 获取矩阵的值
``` kotlin
println(mat[i, j])
```
或者直接打印矩阵
```kotlin
print(mat)
```

3. 设置矩阵的值
``` kotlin
mat[i, j] = 10.0
```

4. 转置
``` kotlin
println(mat.T)
```

5. 求逆矩阵
``` kotlin
println(mat.I)
```

6. 加法
``` kotlin
println(mat + mat2)
println(mat + 2.0)
```

7. 减法
``` kotlin
println(mat - mat2)
println(mat - 2.0)
```

8. 逐元素乘法
``` kotlin
println(mat * mat2)
println(mat * 2.0)
```

9. 点乘
``` kotlin
println(mat.dot(mat2))
```

10. 逐元素出发
``` kotlin
println(mat / mat2)
println(mat / 2.0)
```

11. 行列式
``` kotlin
println(mat.det())
```

12. 迹运算
``` kotlin
println(mat.trace())
```

13. 伴随矩阵
``` kotlin
println(mat.adj())
```
