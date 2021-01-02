package org.ironica.amatsukaze

/*
func foo(a: Int, b: String): Int {
    print(b)
    a += 1
    return a
}
cst b = 3
cst c = foo(b, "bar")
print(c)
 */
/*
cst a = 1
func foo() {
    cst a = 2
    print(a)
}

print(a)
foo()
print(a)
 */



// ver 2.0

/*
cst add = (a: Int): Int -> {
	return func(b: Int): Int {
		return a + b
	}
}

func mul(a: Int): Int {
	return func(b: Int): Int {
		return a * b
	}
}

cst add3 = add(3)
cst mul4 = mul(4)

print(add3(mul4(4)))
 */
/*
var a = 2
var b = 3

func foo(&a: Int, b: Int) {
		a += 1
		b += 1
}
foo(&a, b)
print(a)
print(b)
 */
/*
cst a: [String] = []
a.pushFront("foo")

for x in a {
    print(x)
}

cst a: Array<Int> = []
a.pushFront(1)

for x in a {
    print(x)
}

cst a: Array<Array<Int>> = []
cst b: [Int] = []
a.pushFront(b)
 */
/*
cst foo = (a: Int) -> {
	(b: Int) -> {
		(c: Int) -> {
			a + b + c
		}
	}
}

cst bar = foo(1)
cst barr = bar(2)
cst barrr = barr(3)
print(barrr)
 */
// Bubblesort
/*
func bubble(a: Array<Int>): [Int] {
  for i in 1 ..< a.size {
    for j in 0 ..< a.size - i {
      if a.at(j) > a.at(j+1) {
        a.swap(j, j + 1)
      }
    }
  }
  return a
}

cst a = [9,4,6,13,2,19,7,5,7,1,22,8,6]
for x in bubble(a) {
  print(x)
}
 */
// QuickSort
/*
func quickSort(a: [Int], left: Int, right: Int): [Int] {
  if left < right {
    cst index = partition(a, left, right)
    quickSort(a, left, index - 1)
    quickSort(a, index + 1, right)
  }
  return a
}

func partition(a: [Int], left: Int, right: Int): Int {
  cst pivot = left
  var index = pivot + 1
  for i in index ... right {
    if a.at(i) < a.at(pivot) {
      a.swap(i, index)
      index += 1
    }
  }
  a.swap(pivot, index - 1)
  return index - 1
}

cst a = [9,4,6,13,2,19,7,5,7,1,22,8,6]
quickSort(a, 0, a.size - 1)
for x in a {
  print(x)
}
 */