import random

def generate_message(jmbm, field_name=None, value_min=None, value_max=None):
    if field_name == None:
        return { "jmbm": jmbm }
    
    value = random.randint(value_min, value_max)
    return {
        "jmbm": jmbm,
        field_name: value 
    }