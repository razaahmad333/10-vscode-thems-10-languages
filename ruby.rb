def sum_eq_n?(arr, n)
    return true if arr.empty? && n == 0
  
    arr.product(arr).reject { |a,b| a == b }.any? { |a,b| a + b == n }
end

def find_missing(sequence)
    consecutive     = sequence.each_cons(2)
    differences     = consecutive.map { |a,b| b - a }
    sequence        = differences.max_by { |n| differences.count(n) }
  
    missing_between = consecutive.find { |a,b| (b - a) != sequence }
  
    missing_between.first + sequence
end
  
find_missing([2,4,6,10])


differences = [2, 2, 4]
differences.max_by { |n| differences.count(n) }

def alternating_characters?(s)
    type = [/[aeiou]/, /[^aeiou]/].cycle
    if s.start_with?(/[^aeiou]/)
      type.next
    end
    s.chars.all? { |ch| ch.match?(type.next) }
end
alternating_characters?("ateciyu")


def get_numbers(list, index = 0, taken = [])
    return [taken] if index == list.size
    get_numbers(list, index+1, taken) +
    get_numbers(list, index+1, taken + [list[index]])
end
get_numbers([1,2,3])


def get_numbers_stack(list)
    stack  = [[0, []]]
    output = []
  
    until stack.empty?
      index, taken = stack.pop
  
      next output << taken if index == list.size
  
      stack.unshift [index + 1, taken]
      stack.unshift [index + 1, taken + [list[index]]]
    end
  
    output
end


def longest_repetition(string)
    max = string
            .chars
            .chunk(&:itself)
            .map(&:last)
            .max_by(&:size)
    max ? [max[0], max.size] : ["", 0]
end
longest_repetition("aaabb")