'''

Given two arrays arr1 and arr2, the elements of arr2 are distinct, and all elements in arr2 are also in arr1.

Sort the elements of arr1 such that the relative ordering
of items in arr1 are the same as in arr2. Elements that do not appear in arr2 should be placed at the end of arr1
in ascending order.


'''

from collections import Counter
class Solution:
    def foo(self, a1, a2):
        countt=Counter(a1)
        r=[]
        for i in a2:
            r.extend(countt[i]*[i])
        r+=sorted([i for i in a1 if i not in r])
        return r

p=Solution()
print(p.foo([1,2,3,5,9,2,1,3,16,89,4], [2,1,3,9]))

