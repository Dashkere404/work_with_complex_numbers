public class Complex {
    private double valid;
    private double image;
    public Complex() {
        this.valid = 0;
        this.image = 0;
    }
    public Complex(double valid, double image) {
        this.valid = valid;
        this.image = image;
    }
    public void setValid(double valid){
        this.valid=valid;
    }
    public double getValid(){
        return valid;
    }
    public void setImage(double image){
        this.image=image;
    }
    public double getImage(){
        return image;
    }
    public Complex sum(Complex a){
        return new Complex(this.valid+a.valid, this.image+a.image);
    }
    public Complex sub(Complex a){
        return new Complex(this.valid-a.valid, this.image-a.image);
    }
    public Complex mul_res(Complex a){
        return new Complex (
                this.valid * a.valid - this.image * a.image,
                this.image * a.valid + this.valid * a.image);
    }
    public Complex divide (Complex a){
        return new Complex(
                (this.valid * a.valid + this.image * a.image) / (a.valid * a.valid + a.image * a.image),
                (a.valid * this.image - this.valid * a.image) / (a.valid * a.valid + a.image * a.image)
        );
    }
    @Override
    public String toString(){
        String formatvalid = String.format("%.2f", valid);
        String formatimage = String.format("%.2f", Math.abs(image));
        if (image>=0)
            return formatvalid + " + " + formatimage + "i" ;
        else
            return formatvalid + " - " + formatimage + "i";
    }

}

