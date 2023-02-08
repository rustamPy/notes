from typing import Any

import urllib3
import random


class Game:
    def __init__(self, wordle: bool = True):
        if wordle:
            self.URL = 'https://gist.githubusercontent.com/dracos/' \
                       'dd0668f281e685bad51479e5acaadb93/raw/ca901' \
                       '8b32e963292473841fb55fd5a62176769b5/valid-' \
                       'wordle-words.txt'
        else:
            self.URL = 'https://raw.githubusercontent.com/dwyl/english-words/master/words.txt'

    def find_word(self, include: str = None, exclude: str = None, begins_with: str = None, ends_with: str = None,
                  length: int = None, order: str = None, disorder: Any = None, min_size: int = None,
                  max_size: int = None, include_only: str = None) -> list:
        """
        :param include: the words will have al of these letters
        :param exclude: the words will not have all of these letters
        :param begins_with: the words will start with this letter or letters (order matters)
        :param ends_with: the words will end with this letter or letters (order matters)
        :param length: the words will be this length
        :param order: the words will have this order of letters
        :param disorder: the words will not have this order of letters
        :param min_size: the words will have minimum this number of letters
        :param max_size: the words will have maximum this number of letters
        :param include_only: the words will only include this set of values
        :return: list
        """

        http = urllib3.PoolManager()
        txt_file = http.request('GET', self.URL).data.decode('utf-8').splitlines()

        base = [w for w in txt_file if len(w) == length] if length else txt_file
        min_base = [w for w in base if len(w) >= int(min_size)] if min_size else base
        max_base = [w for w in min_base if len(w) <= int(max_size)] if max_size else min_base
        include_base = [w for w in max_base if
                        all([val.lower() in w.lower() for val in include])] if include else max_base
        exclude_base = [w for w in include_base if
                        all([val.lower() not in w.lower() for val in exclude])] if exclude else include_base

        include_only_ls = [w for w in exclude_base if
                           all([char.lower() in include_only.lower() for char in w])] if include_only else exclude_base

        begins_with_ls = [w for w in include_only_ls if w.startswith(begins_with)] if begins_with else include_only_ls
        ends_with_ls = [w for w in begins_with_ls if
                        w.lower().endswith(ends_with.lower())] if ends_with else begins_with_ls

        order_ls = [w for w in ends_with_ls if
                    all([w[i] == order[i] for i in range(len(order)) if order[i] != '_'])] if order else ends_with_ls
        disorder_ls = [word for word in order_ls
                       if (not isinstance(disorder, list) and all(
                [word[i] != disorder[i] for i in range(len(disorder))])) or
                       (isinstance(disorder, list) and all([all([word[i] != disord[i] for i in range(len(disord))])
                                                            for disord in disorder]))] if disorder else order_ls

        return disorder_ls

    """
    History:
    1. 
    Query: example = obj.find_word(exclude='brekcvsn', include='ail', disorder=['___a_', '_a___'], order='__ail')
    Answer: FLAIL

    """

    def lucky_you(self, ls: list = None):
        for _ in range(len(ls)):
            rand = random.choice(ls)
            if len(set(rand)) == len(list(rand)):
                return rand
        return random.choice(ls)


if __name__ == "__main__":
    obj = Game()
    example = obj.find_word(exclude='rdiojcksnum', disorder=['f____'], order='_a___', include='af')
    print(obj.lucky_you(example))
    print(len(example))
    print(example)
