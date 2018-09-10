class Map {
    Map(type) {
        this.type = type;
        this.map = map(type);
    }

    add(key, val) {
        mapAdd(this.map, key, val);
    }

    get(key) {
        return getItem(this.map, key);
    }

    reset() {
        this.map = map(this.type);
    }

    show() {
        say this.map;
    }
}