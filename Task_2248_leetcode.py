'''

Given a 2D integer array nums where nums[i] is a non-empty array of
distinct positive integers, return the list of integers that are present in each array of nums sorted in ascending order.

'''


class Solution(object):
    def intersection(self, nums):
        if len(nums)==1:
            return sorted([i for i in nums[0]])
        ls=[]
        c=1
        for i in nums[0]:
            if i not in nums[c]:
                continue
            while c<len(nums) and i in nums[c] :
                c+=1
            if c==len(nums):
                ls.append(i)
                c=1
            else:
                c=1
        ls=sorted(ls)
        return ls
