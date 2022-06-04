import random

def generate_message(field_name, jmbm, value_min, value_max):
    if field_name == None:
        return { "jmbm": jmbm }
    
    value = random.randint(value_min, value_max)
    return {
        "jmbm": jmbm,
        field_name: value 
    }