import urllib3


def find_word(include: str = None, exclude: str = None, begins_with: str = None, ends_with: str = None,
              length: int = None, order: str = None, disorder: str = None, min_size: int = None, max_size: int = None,
              include_only: str = None, wordle: bool = True) -> list:
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
    :param wordle: turn on wordle words
    :return: list
    """

    wordle_url = 'https://gist.githubusercontent.com/dracos/dd0668f281e685bad51479e5acaadb93/raw/ca9018b32e963292473841fb55fd5a62176769b5/valid-wordle-words.txt'
    all_words_url = 'https://raw.githubusercontent.com/dwyl/english-words/master/words.txt'
    http = urllib3.PoolManager()
    wordle_txt = http.request('GET', wordle_url).data.decode('utf-8').splitlines()
    all_words_txt = http.request('GET', all_words_url).data.decode('utf-8').splitlines()

    order_dict = {i: val for i, val in enumerate(order) if val != '_'} if order else {}
    disorder_dict = {i: val for i, val in enumerate(disorder) if val != '_'} if disorder else {}
    words_base = wordle_txt if wordle else all_words_txt

    base = [w for w in words_base if len(w) == length] if length else words_base
    min_base = [w for w in base if len(w) >= int(min_size)] if min_size else base
    max_base = [w for w in min_base if len(w) <= int(max_size)] if max_size else min_base
    include_base = [w for w in max_base if
                    all([val.lower() in w.lower() for val in include])] if include else max_base
    exclude_base = [w for w in include_base if
                    all([val.lower() not in w.lower() for val in exclude])] if exclude else include_base

    include_only_ls = [w for w in exclude_base if
                       all([char.lower() in include_only.lower() for char in w])] if include_only else exclude_base

    begins_with_ls = [w for w in include_only_ls if w.startswith(begins_with)] if begins_with else include_only_ls
    ends_with_ls = [w for w in begins_with_ls if w.lower().endswith(ends_with.lower())] if ends_with else begins_with_ls

    order_ls = [w for w in ends_with_ls if
                all([w[i] == order_dict[i] for i in order_dict])] if order_dict else ends_with_ls
    disorder_ls = [w for w in order_ls if
                   all([w[i] != disorder_dict[i] for i in disorder_dict])] if disorder else order_ls

    return disorder_ls


a = find_word(include='el', disorder='__de_', exclude='hoarft', order='_l___', length=5)
