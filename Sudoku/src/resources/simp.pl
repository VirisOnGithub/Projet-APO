use strict;
use warnings;

open my $fh, '<', 'file.txt' or die "Can't open file: $!";

my @grid;

while (my $line = <$fh>) {
    chomp $line;
    my @line = split //, $line;
    push @grid, \@line;
}

close $fh;

open $fh, '>', 'file.txt' or die "Can't open file: $!";

for my $i (0 .. $#grid) {
    print $fh join ',', @{ $grid[$i] };
    print $fh "\n";
}